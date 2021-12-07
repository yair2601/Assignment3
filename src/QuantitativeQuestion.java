public class QuantitativeQuestion extends Question {
	private String formula;
	public QuantitativeQuestion (String content, int level, char answer, String[] choices, String formula) {
		super(content, level, answer, choices);
		this.formula=formula;
	}
	public void updateLevel() {
		int wrongPrecent=totalWrongAnswers/totalAnswers;
		if(wrongPrecent>0.7) {
			if (checkLevelRange(level,1)){
				level++;
			}
		}
		if(wrongPrecent<0.25) {
			if (checkLevelRange(level,-1)){
				level--;
			}
		}
	}
	public String getformula() {
		return this.formula;
	}

}