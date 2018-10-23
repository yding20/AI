import java.util.ArrayList;


public class WumpusWorld {

	ArrayList<Clause> clauses;
	Clause alpha;

	public WumpusWorld() {
		clauses = new ArrayList<>();
		alpha = new Clause();
		Term B11 = new Term("B11", 1);
		Term B21 = new Term("B21", 1);
		Term P11 = new Term("P11", 1);
		Term P12 = new Term("P12", 1);
		Term P21 = new Term("P21", 1);
		Term P22 = new Term("P22", 1);
		Term P31 = new Term("P31", 1);

		Term B11_ = new Term("B11", -1);
		Term B21_ = new Term("B21", -1);
		Term P11_ = new Term("P11", -1);
		Term P12_ = new Term("P12", -1);
		Term P21_ = new Term("P21", -1);
		Term P22_ = new Term("P22", -1);
		Term P31_ = new Term("P31", -1);

		Clause clause1 = new Clause();
		clause1.add(P11_);

		Clause clause2 = new Clause();
		clause2.add(B11_);
		//clause2.add(P12);
		//clause2.add(P21);

		Clause clause3 = new Clause();
		clause3.add(P12_);
		clause3.add(B11);

		Clause clause4 = new Clause();
		clause4.add(P21_);
		clause4.add(B11);

		Clause clause5 = new Clause();
		clause5.add(B21_);
		clause5.add(P11);
		clause5.add(P22);
		clause5.add(P31);

		Clause clause6 = new Clause();
		clause6.add(P11_);
		clause6.add(B21);

		Clause clause7 = new Clause();
		clause7.add(P22_);
		clause7.add(B21);

		Clause clause8 = new Clause();
		clause8.add(P31_);
		clause8.add(B21);

		Clause clause9 = new Clause();
		clause9.add(B11_);

		Clause clause10 = new Clause();
		clause10.add(B21);

		alpha.add(P12_);  //Already Negated

		clauses.add(clause1);
		clauses.add(clause2);
		clauses.add(clause3);
		clauses.add(clause4);
		clauses.add(clause5);
		clauses.add(clause6);
		clauses.add(clause7);
		clauses.add(clause8);
		clauses.add(clause9);
		clauses.add(clause10);

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
//		WumpusWorld WW =  new WumpusWorld();

//		Term B11 = new Term("B11", 1);
//		Term B12 = new Term("B12", 1);
//		Term B13 = new Term("B13", 1);
//		Clause clause1 = new Clause();
//		clause1.add(B11);
//
//		Clause clause2 = new Clause(clause1);
//
//		clause1.add(B12);
//		clause2.add(B13);
//
//		System.out.println(clause1.get());
//		System.out.println(clause2.get());
//
//		clause1.remove(B12);
//
//		System.out.println(clause1.get());
//		System.out.println(clause2.get());
//		WW.getAll();

	}
}