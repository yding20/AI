import java.util.HashMap;
import java.lang.StringBuilder;

public class Symbol {
	protected String name;
	protected String lhs;
	protected String rhs;

	public Symbol(String name) {
		this.name = name;
	}
	public Symbol(String lhs, String rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public boolean isSatisfiedByPre(Model model, HashMap<String, Boolean> compound) {
		System.out.println("0");
		return model.get(name);
	}

	public boolean isSatisfiedBy(Model model, HashMap<String, Boolean> compound) {
		System.out.println("0");
		return model.get(name);
	}

	public boolean isSatisfiedByAlpha(Model model, HashMap<String, Boolean> compound) {
		System.out.println("0");
		return model.get(name);
	}
}