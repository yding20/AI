import java.util.HashMap;
import java.util.ArrayList;

public class Model {
	private HashMap<String, Boolean> assignments;
	private HashMap<String, Boolean> compound = new HashMap<>();

	public Model() {
		assignments = new HashMap<>();
	}

	public Model(Model inputM) {
		assignments = new HashMap<>();

		for (String s: inputM.getAssignments().keySet())
			assignments.put(s, inputM.getAssignments().get(s));
	}


	public void set(String s, boolean b) {
		if (assignments.containsKey(s)) {
			assignments.remove(s);
			assignments.put(s, b);
		}
		else {
			assignments.put(s, b);
		}
	}

	public boolean get(String s) {
		if (assignments.containsKey(s))
			return assignments.get(s);
		else
			return false;
	}

	public HashMap<String, Boolean> getAssignments() {
		return assignments;
	}

	public void output() {
		for (String s: assignments.keySet())
			System.out.println(s + "   :  " + assignments.get(s));
	}

	public void satisfyKBPre(ArrayList<Symbol> kbpre) {
		
		for(Symbol S : kbpre) {
			S.isSatisfiedByPre(this, compound);
		}
		
	}

	public boolean satisfyKB(ArrayList<Symbol> kb) {
		boolean check = true;
//		boolean Ptf =  this.assignments.get("P");
//		boolean Qtf = this.assignments.get("Q");
//		System.out.print("P : "+ Ptf + "   Q : "+ Qtf);

//		boolean B11tf =  this.assignments.get("B11");
//		boolean B21tf =  this.assignments.get("B21");
//		boolean P11tf =  this.assignments.get("P11");
//		boolean P12tf =  this.assignments.get("P12");
//		boolean P21tf =  this.assignments.get("P21");
//		boolean P22tf =  this.assignments.get("P22");
//		boolean P31tf =  this.assignments.get("P31");
		
//		System.out.print("B11: "+ B11tf + "--B21: "+ B21tf+ "--P11: "+ P11tf+ "--P12: "+ P12tf+ "--P21: "+ P21tf
//			+ "--P22: " + P22tf+ "--P32: "+ P31tf);

//		System.out.print(B11tf + "--" + B21tf + "--" + P11tf + "--" + P12tf + "--" + P21tf 
//				+ "--" + P22tf+ "--"+ P31tf + "  ||  ");

//		boolean Amy =  this.assignments.get("Amy");
//		boolean Bob =  this.assignments.get("Bob");
//		boolean Cal =  this.assignments.get("Cal");

//		System.out.print(Amy + "--" + Bob + "--" + Cal + "--"  +  "  ||  ");
	
		for(Symbol S : kb) {
			boolean c = S.isSatisfiedBy(this, compound);
			check = check && c;
		}
//		System.out.println("   Final check : " + check);
		
		return check;
	}

	public boolean satisfy(ArrayList<Symbol> alpha) {
		Symbol S = alpha.get(0);
		boolean b = S.isSatisfiedByAlpha(this, compound);
		return b;

	}





	public static void main(String[] args) {
//		Model model = new Model();
//		model.set("aa", true);
//		model.set("bb", false);
//		model.set("cc", true);
		

//		model.set(new TwoSymbols("AA", "BB"), false);
//
//		Model model2 = new Model(model);
//		model.set("ee", false);
//
//		model2.set("⇒", true);
//		model2.set("¬", true);
//		model2.set("∧", true);
//		model2.set("∨", true);
//		model.output();
//		model2.output();

	}


}