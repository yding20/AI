import java.util.HashMap;
import java.util.Map;

public class ConstantDecisonTree extends DecisionTree {
	private String value;

	public ConstantDecisonTree(String value) {
		//super(value);
		this.value = value;
	}

	@Override
	public Object predict(String[] testData, Map<String, Integer> sequenceMap) {
		//System.out.println("Leaf value : " + value);
		return value;
	}
}