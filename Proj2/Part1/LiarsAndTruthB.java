import java.util.ArrayList;
import java.util.HashMap;

public class LiarsAndTruthB {

	ArrayList<String> symbols = new ArrayList<>();
	ArrayList<Symbol> KB = new ArrayList<>();
	ArrayList<Symbol> KBPre = new ArrayList<>();
	ArrayList<Symbol> Alpha = new ArrayList<>();
	HashMap<String, Boolean> compound = new HashMap<>();	

	public LiarsAndTruthB(String s) {
		symbols.add("Amy");
		symbols.add("Bob");
		symbols.add("Cal");

		NegationSymbol n1 = new NegationSymbol("Cal");
		KBPre.add(n1);
		Iff iff1 = new Iff("Amy", n1.toString());
		KB.add(iff1);

		And and1 = new And("Amy", "Cal");
		KBPre.add(and1);	
		iff1 = new Iff("Bob", and1.toString());
		KB.add(iff1);

		iff1 = new Iff("Cal", "Bob");
		KB.add(iff1);

		Alpha.add(new PureSymbol(s));


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