import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ExactInference {
	public static void main (String[] args) throws Exception{
		ReadXMLFile readFile = new ReadXMLFile();

		String s1 = args[0];
		boolean dogP = s1.equals("dog-problem.xml");
		List<String> X = new ArrayList<>();
		X.add(args[1]);

		List<String> ObEvi = new ArrayList<>();
		Map<String, Boolean> sign = new HashMap<>();

		for (int c = 2; c < args.length; c += 2) {
				ObEvi.add(args[c]);
				if (args[c+1].equals("true")) {
					sign.put(args[c], true);
				} else {
					sign.put(args[c], false);
				}
		}

//		 System.out.println(s1);
//		 for (String ss1 : X)
//		 	System.out.println(ss1);
//		 for (String ss2 : ObEvi)
//		 	System.out.println(ss2 + "****" + sign.get(ss2));

		BayesianNetwork bayesiannetwork = readFile.ReadFile(s1);

		EnumerationAsk test = new EnumerationAsk(bayesiannetwork);
		long start = System.nanoTime();
  		List<Double> ResDist = test.numerationAsk(X, ObEvi, sign, dogP);
  		long now = System.nanoTime();
  		double time = (now - start) / 1000000000.0;
        double val1 = ResDist.get(0);
        double val2 = ResDist.get(1);
        double total = val1 + val2;
        val1  = val1/(total);
        val2  = val2/(total);

        System.out.println("The result ditribution is <" + String.format("%.8f", val1) 
                    + " " + String.format("%.8f", val2) + " >" + "  time : " + String.format("%.8f", time));
	}
}