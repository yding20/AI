import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Test{

	public static BufferedWriter bw;

	public Test() throws IOException  {
		System.out.println("Problem 1 ");
		System.out.print("ModusPonens : ");
		bw.write("Solving ModusPonens : ");
		ModusPonens MP =  new ModusPonens();
		System.out.println("");

		System.out.println("Problem 2");
		System.out.print("WumpusWorld : ");
		bw.write("Solving WumpusWorld : ");
		WumpusWorld WW =  new WumpusWorld();
		System.out.println("");

		System.out.println("Problem 3");
		System.out.print("unicorn is mythical ?  : ");
		bw.write("Solving unicorn is mythical ? : ");
		HornClauses HC1 =  new HornClauses("mythical");
		System.out.print("unicorn is magical ?  : ");
		bw.write("Solving unicorn is magical ? : ");
		HornClauses HC2 =  new HornClauses("magical");
		System.out.print("unicorn is horned ?  : ");
		bw.write("Solving unicorn is horned ? : ");
		HornClauses HC3 =  new HornClauses("horned");
		System.out.println("");


		System.out.println("Problem 4a)");
		System.out.print("Amy  : ");
		bw.write("Solving Problem4 : ");
		LiarsTruthA LTA1 =  new LiarsTruthA("Amy");
		System.out.print("Bob  : ");
		LiarsTruthA LTA2 =  new LiarsTruthA("Bob");
		System.out.print("Cal  : ");
		LiarsTruthA LTA3 =  new LiarsTruthA("Cal");
		System.out.println("");

		System.out.println("Problem 4b)");
		System.out.print("Amy  : ");
		LiarsTruthB LTB1 =  new LiarsTruthB("Amy");
		System.out.print("Bob  : ");
		LiarsTruthB LTB2 =  new LiarsTruthB("Bob");
		System.out.print("Cal  : ");
		LiarsTruthB LTB3 =  new LiarsTruthB("Cal");
		System.out.println("");
	}

	public static boolean PLResolution(ArrayList<Clause> KB, Clause alpha) throws IOException {

		KB.add(alpha);

		ArrayList<Clause> resolvents = new ArrayList<>();
		ArrayList<Clause> newC = new ArrayList<>();

		while (true) {
			for (int i = 0; i < KB.size(); i++) {
				for (int j = i+1; j < KB.size(); j++) {
					Clause clause_i = KB.get(i);
					Clause clause_j = KB.get(j);

//					System.out.println("  " + clause_i.get());
//					System.out.println("  " + clause_j.get());

					resolvents = PLResolve(clause_i, clause_j);
//					System.out.println("resolvents");
//					getAll(resolvents);

					for (int k = 0; k < resolvents.size(); k++) {
						Clause current = resolvents.get(k);
						if (current.isEmpty())	return true;
					}

					ArrayList<String> sssC = new ArrayList<>();
					for (Clause clause : newC) {
						sssC.add(clause.get());
					}

					for (Clause clause : resolvents) {
						if (!sssC.contains(clause.get()))
							newC.add(clause);
					}
				}
			}

			Set<String> set1 = new HashSet<>();
			Set<String> set2 = new HashSet<>();
			for (Clause clause: newC) {
				String s1 = clause.get();
				//System.out.println(s1);
				set1.add(s1);
			}
			for (Clause clause: KB) {
				String s2 = clause.get();
				//System.out.println(s2);
				set2.add(s2);
			}

//			System.out.println("newC");
//			for (String s1 : set1)
//				System.out.println(s1);
//			System.out.println("KB");
//			for (String s2 : set2)
//				System.out.println(s2);

			if (set2.containsAll(set1)) {
				return false;
			}

			ArrayList<String> sssKB = new ArrayList<>();
			for (Clause clause : KB) {
				sssKB.add(clause.get());
			}

			for (Clause clause : newC) {
				if (!sssKB.contains(clause.get()))
					KB.add(clause);
			}

//			KB.addAll(newC);

		}
	}

	public static ArrayList<Clause> PLResolve(Clause clause_i, Clause clause_j) throws IOException {
		ArrayList<Clause> resolvents = new ArrayList<>();

		Clause c_i = new Clause(clause_i);
		Clause c_j = new Clause(clause_j);
		ArrayList<Term> ci = c_i.getArrayList();
		ArrayList<Term> cj = c_j.getArrayList();


		for (int i = 0; i < ci.size(); i++) {
			for (int j = 0; j < cj.size(); j++) {
				Term termi = ci.get(i);
				String si = termi.getString();
				int mi = termi.getInt();

				Term termj = cj.get(j);
				String sj = termj.getString();
				int mj = termj.getInt();

				if (si.equals(sj) && mi != mj) {

//					System.out.println("InsideRev : " + clause_i.get());
//					System.out.println("InsideRev : " + clause_j.get());
					bw.write("\nClauses: " + clause_i.get() + "\t" + clause_j.get());

					c_i.remove(termi);
					c_j.remove(termj);
//					System.out.println("InsideRev2 : " + c_i.get());
//					System.out.println("InsideRev2 : " + c_j.get());
					c_i.combine(c_j);


//					System.out.println("After Rev : " + c_i.get());
					bw.write("\nResolvents: " + c_j.get() + "\n");


				ArrayList<String> sssr = new ArrayList<>();
				for (Clause clause : resolvents) {
					sssr.add(clause.get());
				}
	
				if (!sssr.contains(c_i.get()))
					resolvents.add(c_i);					

					//resolvents.add(c_i);
					return resolvents;
				}
			}
		}
//		resolvents.add(c_i);
//		resolvents.add(c_j);

		ArrayList<String> sssr1 = new ArrayList<>();
		for (Clause clause : resolvents) {
			sssr1.add(clause.get());
		}
	
		if (!sssr1.contains(c_i.get()))
			resolvents.add(c_i);	

		ArrayList<String> sssr2 = new ArrayList<>();
		for (Clause clause : resolvents) {
			sssr2.add(clause.get());
		}
	
		if (!sssr2.contains(c_j.get()))
			resolvents.add(c_j);	

		return resolvents;

	}


	public static void main(String[] args) throws IOException {
		File file = new File("CalculationDetails.txt");

		// If the file doesn't exist then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write("\nStart: " + "\n");
		Test test = new Test();

	}	
}