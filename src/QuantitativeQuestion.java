public class QuantitativeQuestion extends Question {
	private String formula;
	
	public QuantitativeQuestion (String content, int level, char answer, String[] choices, String formula) {
		super(content, level, answer, choices);
		this.formula=formula;
		this.QuationType = "Quantitative";
	}
	public void updateLevel() {
		try {
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
		catch (ArithmeticException e) {//if someone try to update level of new question
			System.out.println("The question is new and therefore its level cannot be updated");
		}
		
	}
	public String getformula() {
		return this.formula;
	}

	public int compare(Question quantitativeQuestion1, Question QuantitativeQuestion2) {
		return this.comperator.compare(this, QuantitativeQuestion2);
		
	}
	
	
	public int compareTo(EnglishQuestion otherQuantitativeQuestion) {
		return this.comperator.compare(this, otherQuantitativeQuestion);
	}
}