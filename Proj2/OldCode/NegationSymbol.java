import java.lang.StringBuilder;
import java.util.HashMap;


public class NegationSymbol extends Symbol {
	public NegationSymbol(String s) {
		super(s);
	}

	public boolean isSatisfiedByPre(Model model, HashMap<String, Boolean> compound) {
		boolean b = true;
		if (model.getAssignments().containsKey(name))
			b = ! model.get(name);
		else 
			b = compound.get(name);

		StringBuilder sb= new StringBuilder();
		sb.append("  ¬  ");
		sb.append(name);
		String s = sb.toString();
		if (compound.containsKey(s)) {
			compound.remove(s);
			compound.put(s, b);
		}
		else {
			compound.put(s, b);
		}
//		System.out.println(s + " : " + b);
		//System.out.print("--" + b);
		return b;
	}

	public boolean isSatisfiedBy(Model model, HashMap<String, Boolean> compound) {
		boolean b = true;
		if (model.getAssignments().containsKey(name))
			b = ! model.get(name);
		else 
			b = compound.get(name);

		StringBuilder sb= new StringBuilder();
		sb.append("  ¬  ");
		sb.append(name);
		String s = sb.toString();
		if (compound.containsKey(s)) {
			compound.remove(s);
			compound.put(s, b);
		}
		else {
			compound.put(s, b);
		}
		//System.out.println(s + " : " + b);
//		System.out.print("--" + b);
		return b;
	}

	public boolean isSatisfiedByAlpha(Model model, HashMap<String, Boolean> compound) {
		boolean b = ! model.get(name);
		StringBuilder sb= new StringBuilder();
		sb.append("  ¬  ");
		sb.append(name);
		String s = sb.toString();
		compound.put(s, b);
//		System.out.println(s + " : " + b);
		return b;
	}

	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append("  ¬  ");
		sb.append(name);
		String s = sb.toString();
		return s;
	}

}