import java.io.File;
import java.io.BufferedReader; 
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ReadXMLFile {
	public BayesianNetwork ReadFile(String s1) throws Exception{

  		File file = new File("examples/" + s1); 
  		// File file = new File("examples/aima-wet-grass.xml");
  		// File file = new File("examples/dog-problem.xml");
  		BufferedReader br = new BufferedReader(new FileReader(file)); 
  		List<List<String>> Variable = new ArrayList<>();
  		List<List<String>> Definition = new ArrayList<>();

  		String st; 
  		while ((st = br.readLine()) != null) {
  			List<String> singLineV = new ArrayList<>();
  			if (st.equals("<VARIABLE TYPE=\"nature\">")) {
//  				System.out.println(st);
  				singLineV.add(st);
  				st = br.readLine();
//  				System.out.println(st);
  				singLineV.add(st);
  				while(!st.equals("</VARIABLE>")) {
  					st = br.readLine();
//  					System.out.println(st);
  					singLineV.add(st);
  				}
  				Variable.add(singLineV);
  			}
  			

  			List<String> singLineD = new ArrayList<>();
  			if (st.equals("<DEFINITION>")) {
//  				System.out.println(st);
  				singLineD.add(st);
  				st = br.readLine();
//  				System.out.println(st);
  				singLineD.add(st);
  				while(!st.equals("</DEFINITION>")) {
  					st = br.readLine();
//  					System.out.println(st);
  					singLineD.add(st);
  				}
  				Definition.add(singLineD);
  			}
  			
  		}

  		GraphElement[] graphElements = new GraphElement[Variable.size()];

  		for (int i = 0; i < Variable.size(); i++) {
  			String name = "";
  			for (int j = 1; j < Variable.get(i).size() - 1; j++) {
  				String s = Variable.get(i).get(j);
  				s = s.trim();
  				if (s.startsWith("<NAME>")) {
  					s = s.replace("<NAME>", "");
  					s = s.replace("</NAME>", "");
  					//System.out.println(s);
  					name = s;
  				}
  			}
  			graphElements[i] = new GraphElement(name);

  			for (int j = 1; j < Definition.get(i).size() - 1; j++) {
  				String s = Definition.get(i).get(j);
  				s = s.trim();
  				if (s.startsWith("<GIVEN>")) {
  					s = s.replace("<GIVEN>", "");
  					s = s.replace("</GIVEN>", "");
  					//System.out.println(s);
  					graphElements[i].addParents(s);
  				} else if (s.startsWith("<TABLE>") && s.endsWith("</TABLE>")) {
  					s = s.replace("<TABLE>", "");
  					s = s.replace("</TABLE>", "");
  					//System.out.println(s);

  					String[] sArr = s.split(" ");
  					for (String ss : sArr) {
  						if (!ss.equals("")) {
  							graphElements[i].addProbabilities(Double.parseDouble(ss));
  							//System.out.println(ss);
  						}
  					}

  					
  				} else if (s.startsWith("<TABLE>")) {
  					while (!s.startsWith("</TABLE>")) {
  						j++;
  						s = Definition.get(i).get(j);
  						s = s.trim();
  						String[] sArr = s.split(" ");

  						for (String ss : sArr) {
  							if (ss.matches("[0-9.]*") && !ss.equals("")) {
  								//System.out.println(ss + " ");
  								graphElements[i].addProbabilities(Double.parseDouble(ss));
  							}
  						}
  					}
  					//System.out.println();
  					break;
  				}
  			}
  			//System.out.println("*****************************");
  		}

  		BayesianNetwork bayesiannetwork = new BayesianNetwork(graphElements);
//  		bayesiannetwork.printNetwork();
        return bayesiannetwork;
	}
}







