import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


public class EnumerationAsk {
	private BayesianNetwork bn;
	private int N; 
	public EnumerationAsk(BayesianNetwork bn) {
		this.bn = bn;
		N = bn.getN();
	}


	public List<Double> numerationAsk(List<String> X, List<String> ObEvi, Map<String, Boolean> sign, boolean dogP) {

		List<Double> ResDist = new ArrayList<>();	
		List<String> vars = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			vars.add(bn.getName(i));
			//System.out.println(bn.getName(i));
		}
		
		if (dogP) {
			String temp = vars.get(0);
			vars.set(0, vars.get(vars.size() - 1));
			vars.set(vars.size() - 1, temp);
		}


		String x = X.get(0);
		ObEvi.add(x);

		for (int i = 0; i < 2; i++) {
			if (i == 0)		sign.put(x, true);
			else			sign.put(x, false);
			ResDist.add(enumerationAll(vars, ObEvi, sign));
		}

//		for (String ss2 : ObEvi)
//		 	System.out.println(ss2 + "****" + sign.get(ss2));

		return ResDist;
	}

	public double enumerationAll(List<String> vars, List<String> ObEvi, Map<String, Boolean> sign) {
		if (vars.isEmpty())	return 1.0;

		List<String> varsNew = new ArrayList(vars);

		String s = varsNew.remove(0);

//		System.out.println("Choose : " + s);

//		for (String ps : bn.getParents(s))
//			System.out.print("   Parent : " + ps);
//		System.out.println();

		List<String> parents = bn.getParents(s);
		List<Double> probabilities = bn.getProbabilities(s);


//		for (String ss2 : ObEvi)
//		 	System.out.print(ss2 + " : " + sign.get(ss2) + "    ");
//		System.out.println();
//		System.out.println("******************");

		if (ObEvi.contains(s)) {
			double p = 0.;
			if (parents.isEmpty()) {
				if (sign.get(s))
					p = probabilities.get(0);
				else
					p = probabilities.get(1);
			} else if (parents.size() == 1) {
				//System.out.println("$$$$" + parents.get(0));
				//System.out.println("****" + sign.get(parents.get(0)));
				int m = sign.get(parents.get(0)) ? 0 : 1;
				int n = sign.get(s) ? 0 : 1;
				p = probabilities.get(2*m + n);
			} else if (parents.size() == 2) {
				int m1 = sign.get(parents.get(0)) ? 0 : 1;
				int m2 = sign.get(parents.get(1)) ? 0 : 1;
				int n = sign.get(s) ? 0 : 1;
				p = probabilities.get(4*m1 + 2*m2 + n);
				
			} 

//			System.out.println("PPPPP : " + p);


			return p * enumerationAll(varsNew, ObEvi, sign);
		} else {

			List<String> ObEviNew = new ArrayList(ObEvi);
			Map<String, Boolean> signNew1 = new HashMap(sign);
			Map<String, Boolean> signNew2 = new HashMap(sign);

			double p1 = 0.;
			double p2 = 0.;

			if (parents.isEmpty()) {
				p1 = probabilities.get(0);
				p2 = probabilities.get(1);
			}else if (parents.size() == 1) {
				int m = sign.get(parents.get(0)) ? 0 : 1;
				p1 = probabilities.get(2*m + 0);
				p2 = probabilities.get(2*m + 1);
			}else if (parents.size() == 2) {
				int m1 = sign.get(parents.get(0)) ? 0 : 1;
				int m2 = sign.get(parents.get(1)) ? 0 : 1;
				p1 = probabilities.get(4*m1 + 2*m2 + 0);
				p2 = probabilities.get(4*m1 + 2*m2 + 1);
			} 
//			System.out.println("P1 : " + p1 + "  P2 : " + p2);

			ObEviNew.add(s);
			signNew1.put(s, true);
			signNew2.put(s, false);
			return p1 * enumerationAll(varsNew, ObEviNew, signNew1) + 
								p2 * enumerationAll(varsNew, ObEviNew, signNew2);

		}
	}











}