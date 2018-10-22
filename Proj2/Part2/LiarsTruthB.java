import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class LiarsTruthB {

	ArrayList<Clause> clauses;
	Clause alpha;

	public LiarsTruthB(String s) throws IOException  {
		clauses = new ArrayList<>();
		alpha = new Clause();
		Term Amy = new Term("Amy", 1);
		Term Amy_ = new Term("Amy", -1);

		Term Bob = new Term("Bob", 1);
		Term Bob_ = new Term("Bob", -1);

		Term Cal = new Term("Cal", 1);
		Term Cal_ = new Term("Cal", -1);

		Clause clause1 = new Clause();
		clause1.add(Amy);
		clause1.add(Cal);

		Clause clause2 = new Clause();
		clause2.add(Amy_);
		clause2.add(Cal_);

		Clause clause3 = new Clause();
		clause3.add(Bob_);
		clause3.add(Amy);

		Clause clause4 = new Clause();
		clause4.add(Bob_);
		clause4.add(Cal);

		Clause clause5 = new Clause();
		clause5.add(Cal_);
		clause5.add(Bob);

		Clause clause6 = new Clause();
		clause6.add(Amy_);
		clause6.add(Cal_);
		clause6.add(Bob);

		if (s.equals("Amy")) {
			alpha.add(Amy_);  //Already Negated
		}

		if (s.equals("Bob")) {
			alpha.add(Bob_);  //Already Negated
		}

		if (s.equals("Cal")) {
			alpha.add(Cal_);  //Already Negated
		}

		clauses.add(clause1);
		clauses.add(clause2);
		clauses.add(clause3);
		clauses.add(clause4);
		clauses.add(clause5);
		clauses.add(clause6);

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
		//LiarsTruthB LTB =  new LiarsTruthB();


	}
}