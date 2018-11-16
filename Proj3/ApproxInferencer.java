import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ApproxInferencer {
	private int N;
	private BayesianNetwork bn;
	private List<String> X = new ArrayList<>();
	private	List<String> ObEvi = new ArrayList<>();
	private	Map<String, Boolean> sign = new HashMap<>();
	private List<String> vars = new ArrayList<>();

	public ApproxInferencer(String[] args) throws Exception {
		ReadXMLFile readFile = new ReadXMLFile();
		String s1 = args[0];
		boolean dogP = s1.equals("dog-problem.xml");
		X.add(args[1]);
		for (int c = 2; c < args.length; c += 2) {
				ObEvi.add(args[c]);
				if (args[c+1].equals("true")) {
					sign.put(args[c], true);
				} else {
					sign.put(args[c], false);
				}
		}
		bn = readFile.ReadFile(s1);
		N = bn.getN();

		for (int i = 0; i < N; i++) {
			vars.add(bn.getName(i));
			//System.out.println(bn.getName(i));
		}

		if (dogP) {
			String temp = vars.get(0);
			vars.set(0, vars.get(vars.size() - 1));
			vars.set(vars.size() - 1, temp);
		}
	}

	public int[] PriorSample() {
		int postive = 0, negative = 0, total = 0;
		int [] Res = new int[2];
		for (int i = 0; i < 5000000; i++)  {
			Map<String, Boolean> ResEvent = new HashMap<>();
			double p = 0.;
			boolean RanSign = true;
			for (String var : vars) {
				List<String> parents = bn.getParents(var);
				List<Double> probabilities = bn.getProbabilities(var);
				double RandomNumber = Math.random();
				if (parents.isEmpty()) {
					p = probabilities.get(0);
					RanSign = (RandomNumber < p)? true : false; 
				} else if (parents.size() == 1) {
					int m = ResEvent.get(parents.get(0)) ? 0 : 1;
					p = probabilities.get(m*2);
					RanSign = (RandomNumber < p)? true : false; 
				} else if (parents.size() == 2) {
					int m1 = ResEvent.get(parents.get(0)) ? 0 : 1;
					int m2 = ResEvent.get(parents.get(1)) ? 0 : 1;
					p = probabilities.get(m1*4 + m2*2);
					RanSign = (RandomNumber < p)? true : false; 
				}
	
				if (sign.containsKey(var) && (sign.get(var) != RanSign)) {
					break;
				};
	
				ResEvent.put(var, RanSign);
				//System.out.print(var + " : " + RanSign + "  ");
			}

			for (String s : ResEvent.keySet()) {
				if (ResEvent.size() == vars.size()) {
					if (ResEvent.get(X.get(0)))	postive++;
					else					negative++;
					total++;
				}
			}

		}
		System.out.println("<" + 1.0*postive/total + ", " + 1.0*negative/total + ">");
		return Res;
	}

	public static void main(String[] args) throws Exception {
		ApproxInferencer approxInferencer = new ApproxInferencer(args);
		approxInferencer.PriorSample();
	}
}