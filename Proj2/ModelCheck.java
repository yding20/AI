import java.util.ArrayList;


public class ModelCheck {

	public static boolean TTEntails(ArrayList<Symbol> kbpre, ArrayList<Symbol> kb, 
										ArrayList<Symbol> alpha, ArrayList<String> symbols) {
		Model model = new Model();

		return TTCheckAll(kbpre, kb, alpha, symbols, model);
	}

	public static boolean TTCheckAll(ArrayList<Symbol> kbpre, ArrayList<Symbol> kb, ArrayList<Symbol> alpha, 
								ArrayList<String> symbols, Model model) {

		if (symbols.isEmpty() == true) {
			if (!kbpre.isEmpty())
				model.satisfyKBPre(kbpre);

			if (model.satisfyKB(kb) == true)
				return model.satisfy(alpha);
			else
				return true;
		}
		else {
			ArrayList<String> symbols2 = new ArrayList<>(symbols);

			String s = symbols.remove(0);
			Model m1 = new Model(model);
			m1.set(s, true);

			s = symbols2.remove(0);
			Model m2 = new Model(model);
			m2.set(s, false);
//			System.out.println(s);
			return TTCheckAll(kbpre, kb, alpha, symbols, m1) && TTCheckAll(kbpre, kb, alpha, symbols2, m2);
		}

//		model.set("B11", true);
//		model.set("B21", false);
//		model.set("P11", false);
//		model.set("P12", false);
//		model.set("P21", false);
//		model.set("P22", false);
//		model.set("P31", false);
//		if (!kbpre.isEmpty())
//			model.satisfyKBPre(kbpre);
//		model.satisfyKB(kb);
//		return true;
	}
}