import java.io.File;
import java.io.BufferedReader; 
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

public class DataSetInitial {

	List<String[]> dataSet = new ArrayList<>();
	List<String> attributes = new ArrayList<>();
	Map<String, List<String>> map = new HashMap<>();
	Map<String, Integer> sequenceMap = new HashMap<>();
	private int whichFile;

	public DataSetInitial(String s1, int whichFile) throws Exception {
		File file = new File(s1); 
		this.whichFile = whichFile;
		BufferedReader br = new BufferedReader(new FileReader(file)); 

		String st;
		while ((st = br.readLine()) != null) {
			//System.out.println(st);
			String[] values = st.split(",");	
			dataSet.add(values);
		}

		if (whichFile == 1) {
			attributes.add("Alternate");
			attributes.add("Bar");
			attributes.add("FriSat");
			attributes.add("Hungry");
			attributes.add("Patrons");
			attributes.add("Price");
			attributes.add("Rain");
			attributes.add("Reservation");
			attributes.add("Type");
			attributes.add("WaitEsimatie");
	
	// For test purpose
	//		attributes.add("Patrons");
	//		attributes.add("Hungry");
	//		attributes.add("Type");
	//		attributes.add("FriSat");
	
			List<String> Alternate = Arrays.asList("Yes", "No");
			List<String> Bar = Arrays.asList("Yes", "No");
			List<String> FriSat = Arrays.asList("Yes", "No");
			List<String> Hungry = Arrays.asList("Yes", "No");
			List<String> Patrons = Arrays.asList("None", "Some", "Full");
			List<String> Price = Arrays.asList("$", "$$", "$$$");
			List<String> Rain = Arrays.asList("Yes", "No");
			List<String> Reservation = Arrays.asList("Yes", "No");
			List<String> Type = Arrays.asList("French", "Italian", "Thai", "Burger");
			List<String> WaitEsimatie = Arrays.asList("0-10", "10-30", "30-60", ">60");
			List<String> Classification = Arrays.asList("Yes", "No");
	
			map.put("Alternate", Alternate);
			map.put("Bar", Bar);
			map.put("FriSat", FriSat);
			map.put("Hungry", Hungry);
			map.put("Patrons", Patrons);
			map.put("Price", Price);
			map.put("Rain", Rain);
			map.put("Reservation", Reservation);
			map.put("Type", Type);
			map.put("WaitEsimatie", WaitEsimatie);
			map.put("Classification", Classification);
			
			sequenceMap.put("Alternate", 0);
			sequenceMap.put("Bar", 1);
			sequenceMap.put("FriSat", 2);
			sequenceMap.put("Hungry", 3);
			sequenceMap.put("Patrons", 4);
			sequenceMap.put("Price", 5);
			sequenceMap.put("Rain", 6);
			sequenceMap.put("Reservation", 7);
			sequenceMap.put("Type", 8);
			sequenceMap.put("WaitEsimatie", 9);
			sequenceMap.put("Classification", 10);
		}

		if (whichFile == 2) {
			attributes.add("sepelLength");
			attributes.add("sepelWidth");
			attributes.add("petalLength");
			attributes.add("petalWidth");

			List<String> sepelLength = Arrays.asList("S", "MS", "L", "ML");
			List<String> sepelWidth = Arrays.asList("S", "MS", "L", "ML");
			List<String> petalLength = Arrays.asList("S", "MS", "L", "ML");
			List<String> petalWidth = Arrays.asList("S", "MS", "L", "ML");
			List<String> Classification = Arrays.asList("Iris-setosa", "Iris-versicolor",
																		 "Iris-virginica");

			map.put("sepelLength", sepelLength);
			map.put("sepelWidth", sepelWidth);
			map.put("petalLength", petalLength);
			map.put("petalWidth", petalWidth);
			map.put("Classification", Classification);

			sequenceMap.put("sepelLength", 0);
			sequenceMap.put("sepelWidth", 1);
			sequenceMap.put("petalLength", 2);
			sequenceMap.put("petalWidth", 3);
			sequenceMap.put("Classification", 4);
		}

		if (whichFile == 4) {
			attributes.add("LCORE");
			attributes.add("LSURF");
			attributes.add("LO2");
			attributes.add("LBP");
			attributes.add("LSURFSTBL");
			attributes.add("CORESTBL");
			attributes.add("BPSTBL");
			attributes.add("COMFORT");

			List<String> LCORE = Arrays.asList("high", "mid", "low");
			List<String> LSURF = Arrays.asList("high", "mid", "low");
			List<String> LO2 = Arrays.asList("excellent", "good", "fair", "poor");
			List<String> LBP = Arrays.asList("high", "mid", "low");
			List<String> LSURFSTBL = Arrays.asList("stable", "mod-stable", "unstable");
			List<String> CORESTBL = Arrays.asList("stable", "mod-stable", "unstable");
			List<String> BPSTBL = Arrays.asList("stable", "mod-stable", "unstable");
			List<String> COMFORT = Arrays.asList("05", "07", "10", "15");
			List<String> Classification = Arrays.asList("I", "S", "A");


			map.put("LCORE", LCORE);
			map.put("LSURF", LSURF);
			map.put("LO2", LO2);
			map.put("LBP", LBP);
			map.put("LSURFSTBL", LSURFSTBL);
			map.put("CORESTBL", CORESTBL);
			map.put("BPSTBL", BPSTBL);
			map.put("COMFORT", COMFORT);
			map.put("Classification", Classification);

			sequenceMap.put("LCORE", 0);
			sequenceMap.put("LSURF", 1);
			sequenceMap.put("LO2", 2);
			sequenceMap.put("LBP", 3);
			sequenceMap.put("LSURFSTBL", 4);
			sequenceMap.put("CORESTBL", 5);
			sequenceMap.put("BPSTBL", 6);
			sequenceMap.put("COMFORT", 7);
			sequenceMap.put("Classification", 8);
		}

		if (whichFile == 3) {
			attributes.add("buying");
			attributes.add("maint");
			attributes.add("doors");
			attributes.add("persons");
			attributes.add("lug_boot");
			attributes.add("safety");

			List<String> buying = Arrays.asList("vhigh", "high", "med", "low");
			List<String> maint = Arrays.asList("vhigh", "high", "med", "low");
			List<String> doors = Arrays.asList("2", "3", "4", "5more");
			List<String> persons = Arrays.asList("2", "4", "more");
			List<String> lug_boot = Arrays.asList("small", "med", "big");
			List<String> safety = Arrays.asList("low", "med", "high");
			List<String> Classification = Arrays.asList("unacc", "acc", "good", "vgood");


			map.put("buying", buying);
			map.put("maint", maint);
			map.put("doors", doors);
			map.put("persons", persons);
			map.put("lug_boot", lug_boot);
			map.put("safety", safety);
			map.put("Classification", Classification);

			sequenceMap.put("buying", 0);
			sequenceMap.put("maint", 1);
			sequenceMap.put("doors", 2);
			sequenceMap.put("persons", 3);
			sequenceMap.put("lug_boot", 4);
			sequenceMap.put("safety", 5);
			sequenceMap.put("Classification", 6);
		}

	}

	public List<String[]> getDataSet() {
		return dataSet;
	}

	public List<String> getAttributes() {
		return attributes;
	}

	public Map<String, List<String>> getDomain() {
		return map;
	}

	public Map<String, Integer> getSequence() {
		return sequenceMap;
	}

	public static void main(String[] args) throws Exception {
		DataSetInitial test = new DataSetInitial("AIMA_Restaurant-data.txt", 1);
		List<String[]> dataSet = test.getDataSet();
		String[] sarr = dataSet.get(1);
		for (String s : sarr)
			System.out.println(s);
	}
}