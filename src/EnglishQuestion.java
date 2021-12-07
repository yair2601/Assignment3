
public class EnglishQuestion extends Question{
	private String  hint ;
	
	public EnglishQuestion (String content, int level, char answer, String[] choices, String hint) {
		super(content, level, answer, choices);
		this.hint=hint;
	}
	void updateLevel() {
		if(this.totalWrongAnswers/this.totalAnswers>0.8&&checkLevelRange(this.level, 1)) {
			level++;
		}
		if(this.totalWrongAnswers/this.totalAnswers<0.2&&checkLevelRange(this.level, -1)) {
			level--;
		}
	}

	public String getHint() {
		return this.hint;
	}

}
