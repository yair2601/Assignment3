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
		this.questions=questions;
		this.questions.sort(QuestiontByDifficultyComparator);
		this.student=student;

		this.ad=ad;

	}

	private void AddToStudentRevenue(int amount) {//add x$ to the student revenue
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

	public void runPractice() {//generate a practice and run it
		PrintAD();
		System.out.println("Welcome to the Practice");
		CreatePracticeQuestions();
		updateStudentGrades();
		updateRevenues();
		updateStudentLevel();
		PrintPracticeResult();
	}

	private void updateStudentLevel() {//update the student level
		this.student.updateStudentLevel();
	}

	private void updateRevenues() {//update the student and ad revenues
		AddToStudentRevenue(2);//add 2$ to student revenue
		AddToAdRevenue();

	}

	private void AddToAdRevenue() {//update the ad revenue
		this.ad.AddToAdrofit();
	}
	private void CreatePracticeQuestions() {//generate questions for the practice
		for(int i =0 ; i<this.questions.size();i++) {
			System.out.println("Question number "+ (i+1) + ":");
			char calculatedAnswer=calculateAnswer(i);
			if(this.questions.elementAt(i)instanceof QuantitativeQuestion) {
				RunMathQuestion(i,calculatedAnswer,this.questions.elementAt(i));
			}
			if(this.questions.elementAt(i)instanceof EnglishQuestion) {
				RunEnglishQuestion(i,calculatedAnswer,this.questions.elementAt(i));
			}
		}

	}

	private void updateStudentGrades() {//update the students grades
		updateStudentGrade("math");
		updateStudentGrade("english");
		updateStudentTotalGrade();

	}

	private void updateStudentTotalGrade() {//update the student total grade
		this.student.SetTotalGrade();

	}

	private void PrintPracticeResult() {// print the practice result
		System.out.println("Practice was finished, your math score is:"+ (int)calculateScore("math")+"%");
		System.out.println("Practice was finished, your math score is:"+ (int)calculateScore("english")+"%");

	}

	private void PrintAD() {// print ad
		System.out.println(this.ad);
	}

	private void RunMathQuestion(int location, char calculatedAnswer,Question question) {// run a math question for the practice
		PrintMathFormula(location);
		PrintQuestion(location);
		UpdateCorrectAnswerPractice(calculatedAnswer,location,"math");
		updateTotalQuestionPractice("math");
		updateQuestionCounters(calculatedAnswer,question);
		System.out.println("Your answer:"+ calculatedAnswer);

	}

	private void updateQuestionCounters(char calculatedAnswer,Question question) {// update the question counters
		question.updateTotalAnswers(1);
		if(checkIfWrongAnswer(calculatedAnswer,question)) {
			question.updateTotalWrongAnswers(1);
		}

	}

	private boolean checkIfWrongAnswer(char calculatedAnswer, Question question) {// check if the answer is wrong
		if(question.getAnswer()==calculatedAnswer) {
			return false;		
		}
		return true;
	}

	private void RunEnglishQuestion(int location, char calculatedAnswer,Question question) {// run an English question for the practice
		PrintQuestion(location);
		PrintQuestionHint(location);
		UpdateCorrectAnswerPractice(calculatedAnswer,location,"english");
		updateTotalQuestionPractice("english");
		updateQuestionCounters(calculatedAnswer,question);
		System.out.println("Your answer:"+ calculatedAnswer);
	}

	private void PrintQuestionHint(int location) {// print the hint for English question
		System.out.println(((EnglishQuestion)this.questions.elementAt(location)).getHint());

	}

	private void PrintQuestion(int location) {// print the question content
		System.out.println(this.questions.elementAt(location));
		for(int i=0;i<this.questions.elementAt(location).choices.length;i++) {
			System.out.println((char)(97+i)+". "+this.questions.elementAt(location).choices[i]);
		}
		
	}


	private void PrintMathFormula(int location) {// print the formula for math question
		System.out.println(((QuantitativeQuestion)this.questions.elementAt(location)).getformula());

	}

	private void updateStudentGrade(String questionsType) {// update the student grades in math and English
		if(questionsType.equals("math")) {
			this.student.setMathGrade((double)this.totalCorrectMathAnswers/this.totalMathAnswers);
		}if(questionsType.equals("english")) {
			this.student.setEnglishGrade((double)this.totalCorrectEnglishAnswers/this.totalEnglishAnswers);
		}

	}

	private double calculateScore(String questionsType) { //calculate the practice score
		if(questionsType.equals("math")) {
			return ((double)this.totalCorrectMathAnswers/this.totalMathAnswers*100);
		}if(questionsType.equals("english")) {
			return ((double)this.totalCorrectEnglishAnswers/this.totalEnglishAnswers*100);
		}
		return 0;
	}

	private void updateTotalQuestionPractice(String questionsType) {// update the total amount of questions
		if(questionsType.equals("math")) {
			this.totalMathAnswers++;
		}else {
			this.totalEnglishAnswers++;
		}			

	}

	private void UpdateCorrectAnswerPractice(char calculatedAnswer, int i, String questionsType) {// update the total amount of correct question
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
		long succesProbability = (student.getStudentLevel());
		if((int)(Math.random() * 10)+1 <= succesProbability) {//generate number between 1-10

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
	public Vector<Question> getQuestions() {//get practice question vector
		return this.questions;

	}
	public Student getStudent() {//get the student 
		return this.student;
	}
}
