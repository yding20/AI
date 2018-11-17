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

	public int[] RejectionSampling() {
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

	public int[] LikelihoodWeighting() {
		double postive = 0., negative = 0., total = 0.;
		int [] Res = new int[2];
		for (int i = 0; i < 2000000; i++)  {
			Map<String, Boolean> ResEvent = new HashMap<>();
			Map<String, Boolean> signNew = new HashMap(sign);
			double p = 0.;
			double weight = 1;
			boolean RanSign = true;
			for (String var : vars) {
				List<String> parents = bn.getParents(var);
				List<Double> probabilities = bn.getProbabilities(var);

				if (signNew.containsKey(var)) {
					if (parents.isEmpty()) {
						int n = signNew.get(var) ? 0 : 1;
						weight *= probabilities.get(n);
					} else if (parents.size() == 1) {
						int m = signNew.get(parents.get(0)) ? 0 : 1;
						int n = signNew.get(var) ? 0 : 1;
						weight *= probabilities.get(2*m + n);
						 
					} else if (parents.size() == 2) {
						int m1 = signNew.get(parents.get(0)) ? 0 : 1;
						int m2 = signNew.get(parents.get(1)) ? 0 : 1;
						int n = signNew.get(var) ? 0 : 1;
						weight *= probabilities.get(4*m1 + 2*m2 + n);
					}
					RanSign = signNew.get(var);
					//System.out.println("Contain : " + var + " : " + RanSign + "  " + weight);
				} else {
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
					signNew.put(var, RanSign);
					//System.out.println("NotContain : " + var + " : " + RanSign + "  ");
				}

	
				ResEvent.put(var, RanSign);
				//System.out.print(var + " : " + RanSign + "  ");
			}

			//System.out.println("  Weight :" + weight);

			for (String s : ResEvent.keySet()) {
				if (ResEvent.get(X.get(0)))	postive += weight ;
				else					negative += weight;
				total +=  weight;
			}

		}
		System.out.println("<" + 1.0*postive/total + ", " + 1.0*negative/total + ">");
		return Res;
	}

	// In the Gibbs case, we can keep sign full all the time
	public int[] GibbsAsk() {
		int [] Res = new int[2];
		List<String> nonEvidence = new ArrayList<>();
		for (String var : vars) {
			if (!ObEvi.contains(var)) {
				nonEvidence.add(var);
				sign.put(var, false); // initial nonEvi to all true
			}
		}

		int postive = 0, negative = 0, total = 0;
		for (int i = 0; i < 50000000; i++) {
			for (String var: nonEvidence) {
				double p1 = 1.;
				p1 = getP(var, p1);
				List<String> children = bn.getChildren(var);
				for (String childvar : children) {
					p1 = getP(childvar, p1);
				}

				boolean temp = sign.get(var);
				sign.remove(var);
				sign.put(var, !temp);

				double p2 = 1;
				p2 = getP(var, p2);
				for (String childvar : children) {
					p2 = getP(childvar, p2);
				}

				temp = sign.get(var);
				sign.remove(var);
				sign.put(var, !temp);

				// Attention : no matter the sign of vars,
				// the transition P is same !!!!!
				double p;
				if (!temp == true)
					p = p1/(p1 + p2);
				else 
					p = p2/(p1 + p2);

//				for (String ss : vars)
//					System.out.print(ss + " " + sign.get(ss) + " ");
//				System.out.println(" P : " + p + "Choosen :" + var + "  P1 " + p1 + "  P2 " + p2);

				double RandomNumber = Math.random();
				boolean RanSign = (RandomNumber < p)? true : false;
				sign.remove(var);
				sign.put(var, RanSign);

				//if (i > 250000) {
					if (sign.get(X.get(0)))		postive++;
					else						negative++;
					total++;
				//}

			}
		}
		System.out.println("<" + 1.0*postive/total + ", " + 1.0*negative/total + ">");
		return Res;
	}	

	public double getP (String var, double p) {
		List<String> parents = bn.getParents(var);
		List<Double> probabilities = bn.getProbabilities(var);

		if (parents.isEmpty()) {
			int n = sign.get(var) ? 0 : 1;
			p *= probabilities.get(n);
		} else if (parents.size() == 1) {
			int m = sign.get(parents.get(0)) ? 0 : 1;
			int n = sign.get(var) ? 0 : 1;
			p *= probabilities.get(2*m + n);
			 
		} else if (parents.size() == 2) {
			int m1 = sign.get(parents.get(0)) ? 0 : 1;
			int m2 = sign.get(parents.get(1)) ? 0 : 1;
			int n = sign.get(var) ? 0 : 1;
			p *= probabilities.get(4*m1 + 2*m2 + n);
		}	
		//System.out.println("********* : " + var + "*********" + p);
		return p;	
	}












	public static void main(String[] args) throws Exception {
		ApproxInferencer approxInferencer = new ApproxInferencer(args);
		approxInferencer.RejectionSampling();
		approxInferencer.LikelihoodWeighting();
		approxInferencer.GibbsAsk();
	}
}