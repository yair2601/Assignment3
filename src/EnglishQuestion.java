
public class EnglishQuestion extends Question{
	private String  hint ;
	
	public EnglishQuestion (String content, int level, char answer, String[] choices, String hint) {//constructor
		super(content, level, answer, choices);
		this.hint=hint;
		this.QuationType = "English";
		//this.comperator=new QuestiontByDifficultyComparator()
	}
	void updateLevel() {//update the question level
		try {
			if((double)this.totalWrongAnswers/this.totalAnswers>0.8&&checkLevelRange(this.level, 1)) {
				level++;
				zeroCounters();
			}
			if((double)this.totalWrongAnswers/this.totalAnswers<0.2&&checkLevelRange(this.level, -1)) {
				level--;
				zeroCounters();
			}
		}
		catch (ArithmeticException e) {
		}
	}

	public String getHint() {//getter for hint field
		return this.hint;
	}
	
//	public int compare(Question englishQuestion1, Question englishQuestion2) {
//		return this.comperator.compare(this, englishQuestion2);
//		
//	}
	
	
//	public int compareTo(EnglishQuestion otherEnglishQuestion) {
//		return this.comperator.compare(this, otherEnglishQuestion);
//	}
//	
	public int compareTo(Question otherEnglishQuestion) {//natural compreation
		return this.comperator.compare(this, otherEnglishQuestion);
		
	}


}
