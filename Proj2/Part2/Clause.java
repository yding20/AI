import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Clause {

	ArrayList<Term> clause;

	public Clause() {
		clause = new ArrayList<>();
	}

	public Clause(Clause clauseinput) {
		clause = new ArrayList<>();
		for (Term term : clauseinput.getArrayList())
			clause.add(term);
	}

	public void add(Term term) {
		clause.add(term);
	}

	public String get() {
		String s = new String();
		for (Term term: clause) {
			s = s + term.get();
			s = s + "  ";
		}
		return s.substring(0, s.length());

	}

	public ArrayList<Term> getArrayList() {
		return clause;
	}

	public void remove(Term term) {
		clause.remove(term);
	}

	public void combine(Clause c_j) {
		ArrayList<Term> cj = c_j.getArrayList();
		
		Set<Term> set = new HashSet<>();
		set.addAll(cj);
		set.addAll(clause);
		clause.clear();
		clause.addAll(set);

	}

	public boolean isEmpty() {
		return clause.isEmpty();
	}

//	public boolean containsAll(Clause inputclause) {
//		ArrayList<Term> input = inputclause.getArrayList();
//		for (Term term: input) {
//			if (!clause.contains(term))	return false;
//		}
//		return true;
//	}




}