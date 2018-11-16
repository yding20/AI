import java.util.List;
import java.util.ArrayList;

public class GraphElement{
	private String name;
	private List<String> parents;
	private List<Double> probabilities;
	public GraphElement(String name) {
		this.name = name;
		parents = new ArrayList<>();
		probabilities = new ArrayList<>();
	}

	public void addParents(String s) {
		parents.add(s);
	}

	public String getName() {
		return name;
	}

	public List<String> getParents() {
		return parents;
	}

	public List<Double> getPro() {
		return probabilities;
	}

	public void addProbabilities(Double probability) {
		probabilities.add(probability);
	}

	public void printOut() {
		System.out.println("Name : " + name);
		System.out.print("Parents : ");
		for (String s : parents) 
			System.out.print(s + "  ");
		System.out.println(" ");
		System.out.print("probabilities : ");
		for (Double d : probabilities) 
			System.out.print(d + "  ");
		System.out.println(" ");
		System.out.println("********************************");
	}
}