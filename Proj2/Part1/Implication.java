//import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.HashMap;

public class Implication extends Symbol {
	public Implication(String lhs, String rhs) {
		super(lhs, rhs);
	}

	public boolean isSatisfiedByPre(Model model, HashMap<String, Boolean> compound) {
		boolean b = true;
		if (model.getAssignments().containsKey(lhs))
			b = !model.get(lhs) || model.get(rhs);
		else if (compound.containsKey(lhs))
			b = !compound.get(lhs) || model.get(rhs);
		else 
			System.out.println("NNNNNNNNNNo" + lhs);
		StringBuilder sb= new StringBuilder();
		sb.append(lhs);
		sb.append("  ⇒  ");
		sb.append(rhs);
		String s = sb.toString();
		if (compound.containsKey(s)) {
			compound.remove(s);
			compound.put(s, b);
		}
		else {
			compound.put(s, b);
		}
		//System.out.print("   " + s + " : " + b);
		return b;
	}

	public boolean isSatisfiedBy(Model model, HashMap<String, Boolean> compound) {
		boolean b = true;
		if (model.getAssignments().containsKey(lhs))
			b = !model.get(lhs) || model.get(rhs);
		else if (compound.containsKey(lhs))
			b = !compound.get(lhs) || model.get(rhs);
		else 
			System.out.println("NNNNNNNNNNo" + lhs);
		StringBuilder sb= new StringBuilder();
		sb.append(lhs);
		sb.append("  ⇒  ");
		sb.append(rhs);
		String s = sb.toString();
		if (compound.containsKey(s)) {
			compound.remove(s);
			compound.put(s, b);
		}
		else {
			compound.put(s, b);
		}
		//System.out.print("   " + s + " : " + b);
		return b;
	}


	public boolean isSatisfiedByAlpha(Model model, HashMap<String, Boolean> compound) {
		boolean b = !model.get(lhs) || model.get(rhs);
		StringBuilder sb= new StringBuilder();
		sb.append(lhs);
		sb.append("  ⇒  ");
		sb.append(rhs);
		String s = sb.toString();
		if (compound.containsKey(s)) {
			compound.remove(s);
			compound.put(s, b);
		}
		else {
			compound.put(s, b);
		}
//		System.out.print("   " + s + " : " + b);
		return b;
	}

	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append(lhs);
		sb.append("  ⇒  ");
		sb.append(rhs);
		String s = sb.toString();
		return s;
	}


//	public static void main(String[] args) {
//		ArrayList<Symbol> symbols = new ArrayList<>();
//		PureSymbol s1 = new PureSymbol("P");
//		Implication s2 = new Implication("P", "Q");
//		symbols.add(s1);
//		symbols.add(s2);
//
//		Symbol o1 = symbols.get(0);
//		Symbol o2 = symbols.get(1);
//
////		System.out.println(o1.toString());
////		System.out.println(o2.toString());
//
//		Model model = new Model();
//		model.set("P", true);
//		model.set("Q", true);
//		boolean b1 = o1.isSatisfiedBy(model);
//		System.out.println(b1);
//		boolean b2 = o2.isSatisfiedBy(model);
//		System.out.println(b2);
//
//		model.set("P", true);
//		model.set("Q", false);
//		b1 = o1.isSatisfiedBy(model);
//		System.out.println(b1);
//		b2 = o2.isSatisfiedBy(model);
//		System.out.println(b2);
//
//		model.set("P", false);
//		model.set("Q", true);
//		b1 = o1.isSatisfiedBy(model);
//		System.out.println(b1);
//		b2 = o2.isSatisfiedBy(model);
//		System.out.println(b2);
//
//		model.set("P", false);
//		model.set("Q", false);
//		b1 = o1.isSatisfiedBy(model);
//		System.out.println(b1);
//		b2 = o2.isSatisfiedBy(model);
//		System.out.println(b2);
//
//	}
}