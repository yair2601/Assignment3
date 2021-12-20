public class QuantitativeQuestion extends Question {
	private String formula;

	public QuantitativeQuestion (String content, int level, char answer, String[] choices, String formula) {//constructor
		super(content, level, answer, choices);
		this.formula=formula;
		this.QuationType = "Quantitative";
	}
	public void updateLevel() {//update the question level
		try {
			double wrongPrecent=totalWrongAnswers/totalAnswers;
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
		catch (ArithmeticException e) {//if someone try to update level of new question
		}
		zeroCounters();
	}
	public String getformula() {//getter for formula field.
		return this.formula;
	}


	public int compareTo(Question quantitativeQuestion2) {//natural comperation
		return this.comperator.compare(this, quantitativeQuestion2);
	}
}