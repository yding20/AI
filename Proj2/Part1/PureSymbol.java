import java.util.HashMap;

public class PureSymbol extends Symbol {
	public PureSymbol(String s) {
		super(s);
	}

	public boolean isSatisfiedBy(Model model, HashMap<String, Boolean> compound) {
		boolean b = model.get(name);
		//System.out.print("   " + name + "  :  " + b);
		//System.out.print("--" + b);
		return b;
	}

	public boolean isSatisfiedByAlpha(Model model, HashMap<String, Boolean> compound) {
		boolean b = model.get(name);
	//	System.out.print("   " + name + "  :  " + b);
		return b;
	}

	public String toString() {
		System.out.println("1111");
		return name;
	}
}