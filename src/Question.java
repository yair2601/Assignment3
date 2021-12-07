
abstract class Question {
	protected  String content;
	protected String[] choices;
	protected char answer;
	protected int level;
	
	protected Question(String content, int level, char answer, String[] choices) {
		this.content=content;
		this.choices= cloneChoicesArray(choices);
		this.answer=answer;
		if (checkIfLevelIsValid(level)) {
			this.level=level;
		}
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
}
