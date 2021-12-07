
public class QuantitativeQuestion extends Question {
	private String formula;
	public QuantitativeQuestion (String content, int level, char answer, String[] choices, String formula) {
		super(content, level, answer, choices);
		this.formula=formula;
	}



}
