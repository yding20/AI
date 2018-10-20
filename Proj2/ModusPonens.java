import java.util.ArrayList;

public class ModusPonens {

	ArrayList<String> symbols = new ArrayList<>();
	ArrayList<Symbol> KBPre = new ArrayList<>();
	ArrayList<Symbol> KB = new ArrayList<>();
	ArrayList<Symbol> Alpha = new ArrayList<>();

	public ModusPonens(){
		symbols.add("P");
		symbols.add("Q");

		KB.add(new PureSymbol("P"));
		KB.add(new Implication("P", "Q"));

		Alpha.add(new PureSymbol("Q"));

		if (ModelCheck.TTEntails(KBPre, KB, Alpha, symbols)) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}
	} 

	public static void main(String[] args) {

	}
}