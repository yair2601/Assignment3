import java.util.Comparator;

abstract class Question implements Comparator<Question> {
	protected  String content;
	protected String[] choices;
	protected char answer;
	protected int level;
	protected int totalAnswers;
	protected int totalWrongAnswers;
	protected Comparator<Question> comperator;
	protected String QuationType ;

	protected Question(String content, int level, char answer, String[] choices) {
		this.content=content;
		this.choices= cloneChoicesArray(choices);
		this.answer=answer;
		if (checkIfLevelIsValid(level)) {
			this.level=level;
		}
		this.comperator= new QuestiontByDifficultyComparator();
	}

	private boolean checkIfLevelIsValid(int level) {
		if (level>=1&& level<=10)
			return true;
		else 
			throw new ImpossibleQuestionLevelException();

	}

	private String[] cloneChoicesArray(String[] choices) {
		String[] answer= new String[choices.length];
		for (int i=0;i<choices.length;i++) {
			answer[i]=choices[i];
		}
		return answer;
	}

	public int getLevel() {
		return level;
	}
	
	public int getTotalAnswers() {
		return totalAnswers;
	}
	
	public int getTotalWrongAnswers() {
		return totalWrongAnswers;
	}
	
	public boolean updateTotalAnswers(int TotalAnswers ) {
		if(totalAnswers==1) {
			this.totalAnswers++;
			return true;
		}
		return false;
		
	}
	
	public boolean updateTotalWrongAnswers(int TotalWrongAnswers ) {
		if(TotalWrongAnswers==1) {
			this.totalWrongAnswers++;
			return true;
		}
		return false;
		
	}
	protected boolean checkLevelRange(int level, int indicator) {
		if (indicator==1) {
			if (level<=9)
				return true;
			else 
				return false;
		}
		if (indicator==-1) {
			if (level>=2)
				return true;
			else 
				return false;
		}

		return true;

	}
	abstract void updateLevel();
	
	public String toString() {
		return this.content;
		
	}
	public char getAnswer() {
		return this.answer;
	}
	public Comparator<Question> getComparator() {
		return this.comperator;
	}
	public Comparator<Question> getComperator() {
		return comperator;
	}
	public String getQuationType() {
		return this.QuationType;
	}
	//abstract int compareTo(Question otherQuestion);
}