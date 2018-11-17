import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class BayesianNetwork {
	private int V;
	private int E;
	private String[] names;
	private List<String>[] Parents;
	private List<String>[] Children;
	private List<Double>[] probabilities;
	private Map<String, Integer> map = new HashMap<>();
	private Map<Integer, String> inversemap = new HashMap<>();

	public BayesianNetwork(GraphElement[] graphElements) {
		V = graphElements.length;
		names = new String[V];
		Parents = new ArrayList[V];
		Children = new ArrayList[V];
		probabilities = new ArrayList[V];
		for (int v = 0; v < V; v++) {
			names[v] = graphElements[v].getName();
			map.put(names[v], v);
			inversemap.put(v, names[v]);
			Parents[v] = graphElements[v].getParents();
			probabilities[v] = graphElements[v].getPro();
			// Attention !!!!
			Children[v] = new ArrayList<>();
		}

		for (int v = 0; v < V; v++) {
			for (String s : Parents[v]) {
				if (map.containsKey(s)) {
					int n = map.get(s);
					addChildren(n, v);
				}
			}
		}
	}

	public void addChildren(int i, int j) {
		if (inversemap.containsKey(j)) {
			Children[i].add(inversemap.get(j));
		}
	}

	public int getN() {
		return V;
	}

	public List<String> getParents(String s) {
		return Parents[map.get(s)];
	}

	public List<String> getChildren(String s) {
		return Children[map.get(s)];
	}

	public List<Double> getProbabilities(String s) {
		return probabilities[map.get(s)];
	}


	public String getName(int i) {
		return names[i];
	}

	public void printNetwork() {
		for (int v = 0; v < V; v++) {
			System.out.println("Name : " + names[v]);
			System.out.print("Parents : ");
			for (String s : Parents[v]) 
				System.out.print(s + "  ");
			System.out.println(" ");
			System.out.print("Children : ");
			for (String s : Children[v]) 
				System.out.print(s + "  ");
			System.out.println(" ");
			System.out.print("probabilities : ");
			for (Double d : probabilities[v]) 
				System.out.print(d + "  ");
			System.out.println(" ");
			System.out.println("********************************");
		}
	}


}