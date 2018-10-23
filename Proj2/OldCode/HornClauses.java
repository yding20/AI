import java.util.ArrayList;


public class HornClauses {

	ArrayList<Clause> clauses;
	Clause alpha;

	public HornClauses(String s) {
		clauses = new ArrayList<>();
		alpha = new Clause();
		Term mythical = new Term("mythical", 1);
		Term mythical_ = new Term("mythical", -1);

		Term immortal = new Term("immortal", 1);
		Term immortal_ = new Term("immortal", -1);

		Term mammal = new Term("mammal", 1);
		Term mammal_ = new Term("mammal", -1);

		Term horned = new Term("horned", 1);
		Term horned_ = new Term("horned", -1);

		Term magical = new Term("magical", 1);
		Term magical_ = new Term("magical", -1);

		Clause clause1 = new Clause();
		clause1.add(mythical_);
		clause1.add(immortal);

		Clause clause2 = new Clause();
		clause2.add(mythical);
		clause2.add(mammal);

		Clause clause3 = new Clause();
		clause3.add(immortal_);
		clause3.add(horned);

		Clause clause4 = new Clause();
		clause4.add(mammal_);
		clause4.add(horned);

		Clause clause5 = new Clause();
		clause5.add(horned_);
		clause5.add(magical);

		if (s.equals("mythical")) {
			alpha.add(mythical_);  //Already Negated
		} 

		if (s.equals("magical")) {
			alpha.add(magical_);  //Already Negated
		} 

		if (s.equals("horned")) {
			alpha.add(horned_);  //Already Negated
		} 


		clauses.add(clause1);
		clauses.add(clause2);
		clauses.add(clause3);
		clauses.add(clause4);
		clauses.add(clause5);

		boolean results = Test.PLResolution(clauses, alpha);
		System.out.println(results);
	}

	public void add(Clause clause) {
		clauses.add(clause);
	}

	public void getAll() {
		System.out.println("Knowledge Base : ");
		for (Clause clause : clauses)
			System.out.println("                " + clause.get());
		System.out.println("Negated Alpha : ");
		System.out.println("                " + alpha.get());
	}



	public static void main(String[] args) {
//		HornClauses HC =  new HornClauses();


	}
}