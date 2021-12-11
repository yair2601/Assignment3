import java.util.*;

public class Practice {
	private Student student;
	private Vector<Question> questions;
	private Ad ad;
	private int totalMathAnswers;
	private int totalEnglishAnswers;
	private int totalCorrectMathAnswers;
	private int totalCorrectEnglishAnswers;
	
	public Practice(Student student, Vector<Question> questions, Ad ad) {
		Comparator<Question> QuestiontByDifficultyComparator = new QuestiontByDifficultyComparator();
		this.questions=questions;//need to check if need to clone
		this.questions.sort(QuestiontByDifficultyComparator);
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
			System.out.print("Question number "+ i+1 + ":");
			char calculatedAnswer=calculateAnswer(i);
			if(this.questions.elementAt(i)instanceof QuantitativeQuestion) {
				System.out.println(((QuantitativeQuestion)this.questions.elementAt(i)).getformula());
				System.out.println(this.questions.elementAt(i));
				UpdateCorrectAnswer(calculatedAnswer,i,"math");
				updateTotalQuestion("math");
				
			}
			if(this.questions.elementAt(i)instanceof EnglishQuestion) {				
				System.out.println(this.questions.elementAt(i));
				System.out.println(((EnglishQuestion)this.questions.elementAt(i)).getHint());
				UpdateCorrectAnswer(calculatedAnswer,i,"english");
				updateTotalQuestion("english");
			}
			System.out.println("Your answer:"+ calculatedAnswer);
			
		}
		System.out.println("Practice was finished, your math score is:"+ calculateScore("math"));
		System.out.println("Practice was finished, your math score is:"+ calculateScore("english"));
	}

	private int calculateScore(String questionsType) {
		if(questionsType.equals("math")) {
			return this.totalCorrectMathAnswers/this.totalMathAnswers;
		}if(questionsType.equals("english")) {
			return this.totalCorrectEnglishAnswers/this.totalEnglishAnswers;
		}
		return 0;
	}

	private void updateTotalQuestion(String questionsType) {
		if(questionsType.equals("math")) {
			this.totalMathAnswers++;
		}else {
			this.totalEnglishAnswers++;
		}			
		
	}

	private void UpdateCorrectAnswer(char calculatedAnswer, int i, String questionsType) {
		if(this.questions.elementAt(i).getAnswer()==calculatedAnswer) {
			if(questionsType.equals("math")) {
				this.totalCorrectMathAnswers++;
			}else {
				this.totalCorrectEnglishAnswers++;
			}			
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
