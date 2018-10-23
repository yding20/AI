import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Resolution {

	public static boolean PLResolution(ArrayList<Clause> KB, Clause alpha) {

		public static BufferedWriter bw;

		File file = new File("resolution_proofs.txt");

		// If the file doesn't exist then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		bw = new BufferedWriter(fw);


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

	public static ArrayList<Clause> PLResolve(Clause clause_i, Clause clause_j) {
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

					c_i.remove(termi);
					c_j.remove(termj);
//					System.out.println("InsideRev2 : " + c_i.get());
//					System.out.println("InsideRev2 : " + c_j.get());
					c_i.combine(c_j);


//					System.out.println("After Rev : " + c_i.get());


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


	public static void getAll(ArrayList<Clause> KB) {
		for (Clause clause : KB)
			System.out.println("  " + clause.get());
	}




}