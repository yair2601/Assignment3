import java.util.*;

public class Practice {
	private Student student;
	private Vector<Question> questions;
	private Ad ad;
	private int totalMathAnswers;
	private int totalEnglishAnswers;
	private int totalCorrectMathAnswers;
	private int totalCorrectEnglishAnswers;

	public Practice(Student student, Vector<Question> questions, Ad ad) {//constructor
		Comparator<Question> QuestiontByDifficultyComparator = new QuestiontByDifficultyComparator();
		this.questions=questions;//need to check if need to clone
		this.questions.sort(QuestiontByDifficultyComparator);
		this.student=student;
		AddToStudentRevenue(2);//add 2$ to student revenue
		this.ad=ad;

	}

	private void AddToStudentRevenue(int amount) {
		this.student.AddToRevenue(amount);
	}

	public Question getQuestionByNumber(int number) {//find the question by her number and return it
		if(number<0||number>this.questions.size()) {
			throw new impossibleIndexOfVector();
		}
		else {
			return this.questions.elementAt(number);
		}
	}

	public int numOfQuestions() {// calculate the number of the question in the practice
		return this.questions.size();
	}

	public void runPractice() {//calculate a practice and run it
		PrintAD();
		System.out.println("Welcome to the Practice");
		for(int i =0 ; i<this.questions.size();i++) {
			System.out.print("Question number "+ i+1 + ":");
			char calculatedAnswer=calculateAnswer(i);
			if(this.questions.elementAt(i)instanceof QuantitativeQuestion) {
				RunMathQuestion(i,calculatedAnswer);
			}
			if(this.questions.elementAt(i)instanceof EnglishQuestion) {
				RunEnglishQuestion(i,calculatedAnswer);
			}
		}
		updateStudentGrade("math");
		updateStudentGrade("english");
		PrintPracticeResult();

	}

	private void PrintPracticeResult() {// print the practice result
		System.out.println("Practice was finished, your math score is:"+ calculateScore("math"));
		System.out.println("Practice was finished, your math score is:"+ calculateScore("english"));

	}

	private void PrintAD() {// print ad
		System.out.println(this.ad);
	}

	private void RunMathQuestion(int location, char calculatedAnswer) {// run a math question for the practice
		PrintMathFormula(location);
		PrintQuestion(location);
		UpdateCorrectAnswer(calculatedAnswer,location,"math");
		updateTotalQuestion("math");
		System.out.println("Your answer:"+ calculatedAnswer);

	}

	private void RunEnglishQuestion(int location, char calculatedAnswer) {// run an English question for the practice
		PrintQuestion(location);
		PrintQuestionHint(location);
		UpdateCorrectAnswer(calculatedAnswer,location,"english");
		updateTotalQuestion("english");
		System.out.println("Your answer:"+ calculatedAnswer);
	}

	private void PrintQuestionHint(int location) {// print the hint for English question
		System.out.println(((EnglishQuestion)this.questions.elementAt(location)).getHint());

	}

	private void PrintQuestion(int location) {// print the question content
		System.out.println(this.questions.elementAt(location));
	}

	private void PrintMathFormula(int location) {// print the formula for math question
		System.out.println(((QuantitativeQuestion)this.questions.elementAt(location)).getformula());

	}

	private void updateStudentGrade(String questionsType) {// update the student grades in math and English
		if(questionsType.equals("math")) {
			this.student.setMathGrade(this.totalCorrectMathAnswers/this.totalMathAnswers);
		}if(questionsType.equals("english")) {
			this.student.setEnglishGrade(this.totalCorrectEnglishAnswers/this.totalEnglishAnswers);
		}

	}

	private int calculateScore(String questionsType) { //calculate the practice score
		if(questionsType.equals("math")) {
			return this.totalCorrectMathAnswers/this.totalMathAnswers;
		}if(questionsType.equals("english")) {
			return this.totalCorrectEnglishAnswers/this.totalEnglishAnswers;
		}
		return 0;
	}

	private void updateTotalQuestion(String questionsType) {// update the total amount of questions
		if(questionsType.equals("math")) {
			this.totalMathAnswers++;
		}else {
			this.totalEnglishAnswers++;
		}			

	}

	private void UpdateCorrectAnswer(char calculatedAnswer, int i, String questionsType) {// update the total amount of correct question
		if(this.questions.elementAt(i).getAnswer()==calculatedAnswer) {
			if(questionsType.equals("math")) {
				this.totalCorrectMathAnswers++;
			}
			else {
				this.totalCorrectEnglishAnswers++;
			}			
		}
	}

	private char calculateAnswer(int i) {// generate an answer according to the student level
		int succesProbability = (student.getStudentLevel()/10);
		if(Math.random()<succesProbability) {

			return this.questions.elementAt(i).getAnswer();
		}
		return randomWrongAnswers(i);
	}

	private char randomWrongAnswers(int i) {// generate a wrong answer
		int randomAnsNumber =(int)(Math.random()*4);
		char randomAns = 'e';
		if(randomAnsNumber==0) {
			randomAns='a';
			if(checkIfWrongAnswer(i,randomAns))
				return 'a';
		}
		if(randomAnsNumber==1) {
			randomAns='b';
			if(checkIfWrongAnswer(i,randomAns))
				return 'b';
		}
		if(randomAnsNumber==2) {
			randomAns='c';
			if(checkIfWrongAnswer(i,randomAns))
				return 'c';
		}
		if(randomAnsNumber==3) {
			randomAns='d';
			if(checkIfWrongAnswer(i,randomAns))
				return 'd';
		}
		return randomWrongAnswers(i);
	}

	private boolean checkIfWrongAnswer(int i , char randomAns) {// check if the answer is wrong
		if(this.questions.elementAt(i).getAnswer()!=randomAns)
			return true;
		else 
			return false;
	}
}
