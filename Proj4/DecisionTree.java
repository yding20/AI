import java.util.HashMap;
import java.util.Map;


public class DecisionTree {
	private String attributeName;
	private HashMap<String, DecisionTree> nodes;
	// This constructor is used for Class ConstantDecisionTree, since no super() used in it.
	protected DecisionTree() {

	}

	public DecisionTree(String attributeName) {
		this.attributeName = attributeName;
		nodes = new HashMap<>();
	}

	public void addLeaf(String attributeValue, String decision) {
		nodes.put(attributeValue, new ConstantDecisonTree(decision));
	}

	public void addNode(String attributeValue, DecisionTree tree) {
		nodes.put(attributeValue, tree);
	}

	public Object predict(String[] testData, Map<String, Integer> sequenceMap) {
		int index = sequenceMap.get(attributeName);
		String attrValue = testData[index];
		//System.out.println(attributeName + " : " + attrValue);
		return nodes.get(attrValue).predict(testData, sequenceMap);
	}

}