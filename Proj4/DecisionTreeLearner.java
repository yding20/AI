import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.File;
import java.io.BufferedReader; 
import java.io.FileReader;

public class DecisionTreeLearner {
	private DecisionTree tree;
	private int iter;

	private DataSetInitial initialdataSet;
	private Map<String, List<String>> map;
	Map<String, Integer> sequenceMap;

	public DecisionTreeLearner(DecisionTree tree, Integer iter, Integer tit) throws Exception {
		this.tree = tree;
		this.iter = iter;

		if (tit == 0) {
			initialdataSet = new DataSetInitial("AIMA_Restaurant-data.txt", 1);
		}
		if (tit == 1) {
			initialdataSet = new DataSetInitial("Iris.data.discrete.txt", 2);
		}
		if (tit == 2) {
			initialdataSet = new DataSetInitial("post-operative.data", 3);
		}

		map = initialdataSet.getDomain();
		sequenceMap = initialdataSet.getSequence();
	}

	public void train() {
		List<String[]> EntireDataSet = initialdataSet.getDataSet();
		List<String[]> dataSet = new ArrayList<>();

		int len = EntireDataSet.size();

		// for (int i = 0; i < iter; i++) {

		// 	int index = (int) (Math.random()*len);
		// 	//System.out.println(index);

		// 	dataSet.add(EntireDataSet.get(index));
		// }
		dataSet = EntireDataSet;
		List<String> attributes = initialdataSet.getAttributes();

		//PluralityValue(dataSet);

		tree = decisionTreeLearning(dataSet, attributes, dataSet);
	}

	public DecisionTree decisionTreeLearning(List<String[]> dataSet, List<String> attributes, List<String[]> dataSetPre) {

		if (dataSet.isEmpty()){
			String s = PluralityValue(dataSetPre);
			// System.out.println("******END : data isEmpty*****" + s);
			// System.out.println();
			return new ConstantDecisonTree(s);
		}

		if (sameClassification(dataSet)) {
			String s = getTarget(dataSet.get(0));
			// System.out.println("******END : same class *****" + s);
			// System.out.println();
			return new ConstantDecisonTree(s);
		}

		if (attributes.isEmpty()){
			String s = PluralityValue(dataSet);
			// System.out.println("******END : attributes is isEmpty*****" + s);
			// System.out.println();
			return new ConstantDecisonTree(s);
		}

		//String nextAttribute = attributes.remove(0);
		String nextAttribute = chooseAttributes(dataSet, attributes);
		//System.out.println(nextAttribute);

		DecisionTree tree = new DecisionTree(nextAttribute);

		List<String> domain = map.get(nextAttribute);
		// String d = domain.get(1);
		// System.out.println(d);
		for (String d : domain) {
			List<String[]> filtered = filterData(dataSet, nextAttribute, d);
			List<String> newAttributes = filterAttribs(attributes, nextAttribute);

			//System.out.println(nextAttribute + " : " + d);
			//printDataSet(filtered);

			DecisionTree subTree = decisionTreeLearning(filtered, newAttributes, dataSet);
			tree.addNode(d, subTree);
		}

		return tree;
	}

	public List<String[]> filterData(List<String[]> dataSet, 
												String nextAttribute, String d) {
		List<String[]> filtered = new ArrayList<>();
		int index = sequenceMap.get(nextAttribute);
		for (String[] sarr : dataSet) {
			if (sarr[index].equals(d)) {
				filtered.add(sarr);
			}
		}
		return filtered;
	}

	private List<String> filterAttribs(List<String> attributes, String nextAttribute) {
		List<String> newAttributes = new ArrayList<>();
		for (String s : attributes) {
			if (!s.equals(nextAttribute)) {
				newAttributes.add(s);
			}
		}
		return newAttributes;
	}

	private boolean sameClassification(List<String[]> dataSet) {
		int len = dataSet.size();
		int index = dataSet.get(0).length - 1;
		for (int i = 1; i < len; i++) {
			if (!dataSet.get(i)[index].equals(dataSet.get(i-1)[index])) {
				return false;
			}
		}
		return true;
	}

	private String getTarget(String[] sarr) {
		int index = sarr.length - 1;
		return sarr[index];
	}

	private String PluralityValue(List<String[]> dataSet) {
		Map<String, Integer> countMap = new HashMap<>();
		for (String[] sarr : dataSet) {
			String s = sarr[sarr.length - 1];
			if (!countMap.containsKey(s)) {
				countMap.put(s, 1);
			} else {
				countMap.put(s, countMap.get(s)+1);
			}
		}
		List<String> list = map.get("Classification");
		String[] keyarra = list.toArray(new String[list.size()]);
		String s = keyarra[0];

		for (int i = 1; i < keyarra.length; i++) {
			String key1 = keyarra[i];
			String key2 = keyarra[i - 1];
			if (countMap.containsKey(key1) && countMap.containsKey(key2)) {
				int i1 = countMap.get(key1);
				int i2 = countMap.get(key2);
				//System.out.println(key1 + " : " + i1);
				//System.out.println(key2 + " : " + i2);
				if (i1 > i2) {
					s = key1;
				}
			}
		}

		return s;
	}

	public String chooseAttributes(List<String[]> dataSet, List<String> attributes) {
		double maxGain = 0.;
		String res = attributes.get(0);
		for (String attrib : attributes) {
			double gain = gainCalculation(dataSet, attributes, attrib);
			if (gain > maxGain) {
				maxGain = gain;
				res = attrib;
			}
		}
		return res;
	}


// Calculating Entropy gain
	public double gainCalculation(List<String[]> dataSet, List<String> attributes, String attrib) {

		List<String> domain = map.get(attrib);
		double totalSize = dataSet.size();
		double remainder = 0.0;

		for (String d : domain) {
			//printDataSet(dataSet);
			List<String[]> filtered = filterData(dataSet, attrib, d);
			//printDataSet(filtered);
			double filteredSize = filtered.size();
			remainder += (filteredSize/totalSize) * funcB(filtered);
		}
		return funcB(dataSet) - remainder;
	}

	public double funcB(List<String[]> dataSet) {
		Map<String, Integer> countMap = new HashMap<>();
		for (String[] sarr : dataSet) {
			String s = sarr[sarr.length - 1];
			if (!countMap.containsKey(s)) {
				countMap.put(s, 1);
			} else {
				countMap.put(s, countMap.get(s)+1);
			}
		}

		double[] data = new double[countMap.keySet().size()];
		Iterator<Integer> iter = countMap.values().iterator();
		for (int i = 0; i < data.length; i++) {
			data[i] = iter.next();
		}
		return entropyCal(data);
	}

	public double entropyCal(double[] probDist) {
		int len = probDist.length;
		double total = 0.0;
		for (double d : probDist) {
			total += d;
		}
		double[] normalized = new double[len];
		if (total != 0) {
			for (int i = 0; i < len; i++) {
				normalized[i] = probDist[i]/total;
			}
		}

		double res = 0.0;
		for (double d : normalized) {
			res += (-1.0 * Math.log(d) / Math.log(2) * d);
		} 
		return res;
	}

// Above part : Calculating Entropy gain


	public void printDataSet(List<String[]> dataSet) {
		System.out.println("The current dataSet : ");
		for (String[] sarr : dataSet) {
			for (String s : sarr) {
				System.out.print(s + "\t");
			}
			System.out.println();
		}
		System.out.println("********************");
	}

	public String predict(String[] testData) {
		return (String) tree.predict(testData, sequenceMap);
	}


	public static void main(String[] args) throws Exception {
		DecisionTree tree = new DecisionTree(); 

		for (int i = 1; i < 2; i++) {
			DecisionTreeLearner example = new DecisionTreeLearner(tree, i, 1);
			example.train();
			//System.out.println("###Training Finished###");
	
			List<String[]> testdataSet = new ArrayList<>();
	
			File file = new File("CSC442_project4/" + "iris.data.discrete_test.txt"); 
			BufferedReader br = new BufferedReader(new FileReader(file)); 
	
			String st;
			while ((st = br.readLine()) != null) {
				//System.out.println(st);
				String[] values = st.split(",");	
				testdataSet.add(values);
			}
	
			int total = 0;
			int correct = 0;
			for (String[] testData : testdataSet) {
				String s = example.predict(testData);
				String target = testData[testData.length - 1];
				if (s.equals(target)) {
					correct++;
				}
				total++;
			}
			System.out.println("The accuracy on test set : " + 1.0 * correct / total);
		}

		for (int i = 1; i < 2; i++) {
			DecisionTreeLearner example = new DecisionTreeLearner(tree, i, 2);
			example.train();
			//System.out.println("###Training Finished###");
	
			List<String[]> testdataSet = new ArrayList<>();
	
			File file = new File("CSC442_project4/" + "post-operative_test.data"); 
			BufferedReader br = new BufferedReader(new FileReader(file)); 
	
			String st;
			while ((st = br.readLine()) != null) {
				//System.out.println(st);
				String[] values = st.split(",");	
				testdataSet.add(values);
			}
	
			int total = 0;
			int correct = 0;
			for (String[] testData : testdataSet) {
				String s = example.predict(testData);
				String target = testData[testData.length - 1];
				if (s.equals(target)) {
					correct++;
				}
				total++;
			}
			System.out.println("The accuracy on test set : " + 1.0 * correct / total);
		}


	}
}