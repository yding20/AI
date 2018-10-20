import java.util.ArrayList;
import java.util.HashMap;

public class HornClauses {

		ArrayList<String> symbols = new ArrayList<>();
		ArrayList<Symbol> KB = new ArrayList<>();
		ArrayList<Symbol> KBPre = new ArrayList<>();
		ArrayList<Symbol> Alpha = new ArrayList<>();
		HashMap<String, Boolean> compound = new HashMap<>();

		public HornClauses(String s) {
			symbols.add("mythical");
			symbols.add("immortal");
			symbols.add("mammal");
			symbols.add("horned");
			symbols.add("magical");
	
			KB.add(new Implication("mythical", "immortal"));
	
			NegationSymbol n1 = new NegationSymbol("mythical");
			KBPre.add(n1);
			Implication im1 = new Implication(n1.toString(), "mammal");
			KB.add(im1);
	
			Or or1 = new Or("immortal", "mammal");
			KBPre.add(or1);
			im1 = new Implication(or1.toString(), "horned");
			KB.add(im1);
	
			KB.add(new Implication("horned", "magical"));
	
			Alpha.add(new PureSymbol(s));
	
			if (ModelCheck.TTEntails(KBPre, KB, Alpha, symbols)) {
				System.out.println("True");
			} else {
				System.out.println("False");
			}

		}

	public static void main(String[] args) {

	}
}