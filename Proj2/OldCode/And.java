import java.lang.StringBuilder;
import java.util.HashMap;


public class And extends Symbol {
	public And(String lhs, String rhs) {
		super(lhs, rhs);
	}


	public boolean isSatisfiedByPre(Model model, HashMap<String, Boolean> compound) {
		boolean b = true;
		if (model.getAssignments().containsKey(rhs))
			b = model.get(lhs) && model.get(rhs);
		else if (compound.containsKey(rhs))
			b = model.get(lhs) && compound.get(rhs);
		else
			System.out.println("NNNNNNNNNNo" + rhs);

		StringBuilder sb= new StringBuilder();
		sb.append(lhs);
		sb.append("  ∧  ");
		sb.append(rhs);
		String s = sb.toString();
		if (compound.containsKey(s)) {
			compound.remove(s);
			compound.put(s, b);
		}
		else {
			compound.put(s, b);
		}
//		System.out.println(s + " : " + b);
		return b;
	}



	public boolean isSatisfiedBy(Model model, HashMap<String, Boolean> compound) {
		boolean b = true;
		if (model.getAssignments().containsKey(rhs))
			b = model.get(lhs) && model.get(rhs);
		else if (compound.containsKey(rhs))
			b = model.get(lhs) && compound.get(rhs);
		else
			System.out.println("NNNNNNNNNNo" + rhs);

		StringBuilder sb= new StringBuilder();
		sb.append(lhs);
		sb.append("  ∧  ");
		sb.append(rhs);
		String s = sb.toString();
		if (compound.containsKey(s)) {
			compound.remove(s);
			compound.put(s, b);
		}
		else {
			compound.put(s, b);
		}
//		System.out.println(s + " : " + b);
//		System.out.print("--" + b);
		return b;
	}


	public boolean isSatisfiedByAlpha(Model model, HashMap<String, Boolean> compound) {
		boolean b = true;
		if (model.getAssignments().containsKey(rhs))
			b = model.get(lhs) && model.get(rhs);
		else 
			b = model.get(lhs) && compound.get(rhs);
		StringBuilder sb= new StringBuilder();
		sb.append(lhs);
		sb.append("  ∧  ");
		sb.append(rhs);
		String s = sb.toString();
//		compound.put("a", b);
//		System.out.println(s + " : " + b);
//		for (String ss: compound.keySet())
//			System.out.println(ss + "   :  " + compound.get(ss));
		return b;
	}

	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append(lhs);
		sb.append("  ∧  ");
		sb.append(rhs);
		String s = sb.toString();
		return s;
	}

}