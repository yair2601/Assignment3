import java.util.Comparator;

abstract class Question implements Comparable<Question>{
	protected  String content;
	protected String[] choices;
	protected char answer;
	protected int level;
	protected int totalAnswers;
	protected int totalWrongAnswers;
	protected Comparator<Question> comperator;
	protected String QuationType ;

	protected Question(String content, int level, char answer, String[] choices) {//constructor
		this.content=content;
		this.choices= cloneChoicesArray(choices);
		this.answer=answer;
		if (checkIfLevelIsValid(level)) {
			this.level=level;
		}
		this.comperator= new QuestiontByDifficultyComparator();
	}

	private boolean checkIfLevelIsValid(int level) {// check if the question level is valid
		if (level>=1&& level<=10)
			return true;
		else 
			throw new ImpossibleQuestionLevelException();

	}

	private String[] cloneChoicesArray(String[] choices) {// clone the choices array
		String[] answer= new String[choices.length];
		for (int i=0;i<choices.length;i++) {
			answer[i]=choices[i];
		}
		return answer;
	}

	public int getLevel() {// get the question level
		return level;
	}

	public int getTotalAnswers() {//get the total answer for the question 
		return totalAnswers;
	}

	public int getTotalWrongAnswers() {//get the total wrong answer for the question 
		return totalWrongAnswers;
	}

	public boolean updateTotalAnswers(int TotalAnswers ) {//update the total answer for the question 
		if(TotalAnswers==1) {
			this.totalAnswers++;
			return true;
		}
		return false;

	}

	public boolean updateTotalWrongAnswers(int TotalWrongAnswers ) {//update the total wrong answer for the question 
		if(TotalWrongAnswers==1) {
			this.totalWrongAnswers++;
			return true;
		}
		return false;

	}
	protected boolean checkLevelRange(int level, int indicator) {// check the level range question
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
	protected void zeroCounters() {//update the questions counters to 0 after update level
		this.totalAnswers=0;
		this.totalWrongAnswers=0;
	}
	abstract void updateLevel();// update question level

	public String toString() {// to string for the question content
		return this.content;

	}
	public char getAnswer() {//get the answer for this question 
		return this.answer;
	}

	public String getQuationType() {// get the question type
		return this.QuationType;
	}

	
}