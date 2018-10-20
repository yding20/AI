import java.util.ArrayList;
import java.util.HashMap;

public class WumpusWorld {
		ArrayList<String> symbols = new ArrayList<>();
		ArrayList<Symbol> KB = new ArrayList<>();
		ArrayList<Symbol> KBPre = new ArrayList<>();
		ArrayList<Symbol> Alpha = new ArrayList<>();
		HashMap<String, Boolean> compound = new HashMap<>();

	public WumpusWorld() {

		symbols.add("B11");
		symbols.add("B21");
		symbols.add("P11");
		symbols.add("P12");
		symbols.add("P21");
		symbols.add("P22");
		symbols.add("P31");

		NegationSymbol n1 = new NegationSymbol("P11");
		KB.add(n1);

		Or or1 = new Or("P12", "P21");
		String tempor1 = or1.toString();
		Iff iff1 = new Iff("B11", tempor1);
		KBPre.add(or1);
		KB.add(iff1);

		or1 = new Or("P22", "P31");
		Or or2 = new Or("P11", or1.toString());
		iff1 = new Iff("B21", or2.toString());
		KBPre.add(or1);
		KBPre.add(or2);
		KB.add(iff1);

		n1 = new NegationSymbol("B11");
		KB.add(n1);

		KB.add(new PureSymbol("B21"));

		Alpha.add(new PureSymbol("P12"));


		if (ModelCheck.TTEntails(KBPre, KB, Alpha, symbols)) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}
	}



	public static void main(String[] args) {

//		Model model = new Model();
//		model.set("B11", true);
//		model.set("B21", false);
//		model.set("P11", false);
//		model.set("P12", false);
//		model.set("P21", false);
//		model.set("P22", false);
//		model.set("P31", false);
//
//
//		n1.isSatisfiedBy(model, compound);
//
//		or1.isSatisfiedBy(model, compound);
//		or2.isSatisfiedBy(model, compound);

//		iff1.isSatisfiedBy(model, compound);

	}
}