import java.util.Comparator;

public class QuestiontByDifficultyComparator implements Comparator<Question> {

	public int compare(Question question1, Question question2) {
		int result=(question1.getLevel() - question2.getLevel());
		if(result==0) {
			return 0;
		}
		if (result>0) {
			return 1;
		}
		if (result<0) {
			return -1;
		}
		return 0;
	}

	

}
