
public class EnglishQuestion extends Question{
	private String  hint ;
	
	public EnglishQuestion (String content, int level, char answer, String[] choices, String hint) {
		super(content, level, answer, choices);
		this.hint=hint;
		this.QuationType = "English";
		//this.comperator=new QuestiontByDifficultyComparator()
	}
	void updateLevel() {
		try {
			if((double)this.totalWrongAnswers/this.totalAnswers>0.8&&checkLevelRange(this.level, 1)) {
				level++;
			}
			if((double)this.totalWrongAnswers/this.totalAnswers<0.2&&checkLevelRange(this.level, -1)) {
				level--;
			}
		}
		catch (ArithmeticException e) {
		}
	}

	public String getHint() {
		return this.hint;
	}
	
	public int compare(Question englishQuestion1, Question englishQuestion2) {
		return this.comperator.compare(this, englishQuestion2);
		
	}
	
	
	public int compareTo(EnglishQuestion otherEnglishQuestion) {
		return this.comperator.compare(this, otherEnglishQuestion);
	}


}
