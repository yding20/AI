import java.lang.StringBuilder;


public class Term {

	private String s;
	private int m;

	public Term(String s, int m) {
		this.s = s;
		this.m = m;
	}

	public String get() {

		if (m == 1)	return s;

		else {
			StringBuilder sb = new StringBuilder();
			sb.append("¬");
			sb.append(s);
			return sb.toString();
		}
	}

	public String getString() {
		return s;
	}

	public int getInt() {
		return m;
	}

	public boolean equals(Term inputterm) {
		if (s.equals(inputterm.getString()) && m == inputterm.getInt()) {
			return true;
		}
		return false;
	}
}