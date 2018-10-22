import java.util.ArrayList;


public class ModusPonens {

	ArrayList<Clause> clauses;
	Clause alpha;

	public ModusPonens() {
		
		clauses = new ArrayList<>();
		alpha = new Clause();
		Term P = new Term("P", 1);
		Term Q = new Term("Q", 1);
		Term P_ = new Term("P", -1);
		Term Q_ = new Term("Q", -1);
		Term R = new Term("R", 1);
		Term R_ = new Term("R", -1);

		Clause clause1 = new Clause();
		clause1.add(P);

		Clause clause2 = new Clause();
		clause2.add(P_);
		clause2.add(Q);

		alpha.add(Q_);  //Already Negated

		clauses.add(clause1);
		clauses.add(clause2);

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
		ModusPonens MP =  new ModusPonens();


	}
}