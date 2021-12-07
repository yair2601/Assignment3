import java.util.*;

public class Practice {
	private Student student;
	private Vector<Question> questions;
	private Ad ad;
	
	public Practice(Student student, Vector<Question> questions, Ad ad) {
		//questions.sort(QuestiontByDifficultyComparator<Question>);
		this.questions=questions;//need to check if need to clone
		this.student=student;
		this.ad=ad;
		
	}
	
	public Question getQuestionByNumber(int number) {
		if(number<0||number>this.questions.size()) {
			throw new impossibleIndexOfVector();
		}else {
			return this.questions.elementAt(number);

		}
	}
	
	public int numOfQuestions() {
		return this.questions.size();
	}
	
	public void runPractice() {
		System.out.println(this.ad);
		System.out.println("Welcome to the Practice");
		for(int i =0 ; i<this.questions.size();i++) {
			System.out.print("Question number"+ i+1 + ":");
			if(this.questions.elementAt(i)instanceof QuantitativeQuestion) {
				System.out.println(((QuantitativeQuestion)this.questions.elementAt(i)).getformula());
				System.out.println(this.questions.elementAt(i));
			}
			if(this.questions.elementAt(i)instanceof EnglishQuestion) {				
				System.out.println(this.questions.elementAt(i));
				System.out.println(((EnglishQuestion)this.questions.elementAt(i)).getHint());
			}
			System.out.println("Your answer:"+ calculateAnswer(i));
		}
	}

	private char calculateAnswer(int i) {
		int succesProbability = (student.getStudentLevel()/10);
		if(Math.random()<succesProbability) {
			
			return this.questions.elementAt(i).getAnswer();
		}
		return randomWrongAnswers(i);
	}

	private char randomWrongAnswers(int i) {
		int randomAnsNumber =(int)(Math.random()*4);
		char randomAns = 'e';
		if(randomAnsNumber==0) {
			randomAns='a';
			if(this.questions.elementAt(i).getAnswer()!=randomAns)
			return 'a';
		}
		if(randomAnsNumber==1) {
			randomAns='b';
			if(this.questions.elementAt(i).getAnswer()!=randomAns)
			return 'b';
		}
		if(randomAnsNumber==2) {
			randomAns='c';
			if(this.questions.elementAt(i).getAnswer()!=randomAns)
			return 'c';
		}
		if(randomAnsNumber==0) {
			randomAns='d';
			if(this.questions.elementAt(i).getAnswer()!=randomAns)
			return 'd';
		}
		return randomWrongAnswers(i);
	}
}
