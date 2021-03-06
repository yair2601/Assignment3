import java.util.*;
import java.io.*;

public class Application {
	private Vector<Question> questions;
	private Vector<Ad> ads;
	private Vector<Student> students;
	private Vector<Practice> practices;




	public Application(String import_questions) throws IOException  {//constructor

		this.questions= new Vector<Question>();
		this.ads= new Vector<Ad>();
		this.students= new Vector<Student>();
		this.practices=new Vector<Practice>();
		importQuestions(import_questions);
	}



	private String[] createChoiceArray(String[] questionPart) {//create array of choises from the choises in the file
		String[] cohiceArray = new String[4];
		for(int i=0;i<cohiceArray.length;i++ ) {
			cohiceArray[i]=questionPart[4+i];
		}
		return cohiceArray;
	}

	public void addStudent(Student s) {//add student to the students vector
		this.students.add(s);
		s.AddToRevenue(10);//add 10$ to student revenue
	}
	public void addAd(Ad ad) {//add ad to the ads vector
		this.ads.add(ad);
	}

	public void addQuestion(Question q) {//add Question to the questions vector
		this.questions.add(q);
	}

	public void importQuestions(String import_questions) throws IOException {//import file
		String Configuration = "C:\\Users\\yair2\\Java\\"+import_questions+".txt";
		BufferedReader inFile=null;
		try
		{
			FileReader fr = new FileReader (Configuration);
			inFile = new BufferedReader (fr);
			String str;
			while ((str = inFile.readLine()) != null){
				String[] questionPart= new String[10];
				questionPart = str.split("\t");
				if(questionPart[0].equals("id")) {
					continue;
				}
				String[] cohiceArray = createChoiceArray(questionPart);
				if(!questionPart[8].equals("")) {
					QuantitativeQuestion question = new QuantitativeQuestion(questionPart[1],Integer.valueOf(questionPart[2]), questionPart[3].charAt(0),cohiceArray,questionPart[8]);
					this.questions.add(question);
				}else {
					EnglishQuestion question = new EnglishQuestion(questionPart[1],Integer.valueOf(questionPart[2]), questionPart[3].charAt(0),cohiceArray,questionPart[9]);
					this.questions.add(question);
				}

			}
		}

		catch (FileNotFoundException exception)
		{
			System.out.println ("The file " + Configuration + " was not found.");
		}
		catch (IOException exception)
		{
			System.out.println (exception);
		}
		finally{
			inFile.close();
		}

	}

	public void createPractice (String email) throws ThereIsNoAppripiateAdException {//create practice for student by email
		int StudentLocation= findStudentLocation(email);
		Vector <Question> practiceQuestion= CreateQuestions(this.students.elementAt(StudentLocation));
		Ad PracticeAd=generateAppropriateAd(StudentLocation);
		Practice practice= new Practice(this.students.elementAt(StudentLocation), practiceQuestion,PracticeAd);
		this.practices.add(practice);
		practice.runPractice();
	}

	public void bestStudents() {//print the name and value of the most profitable and with highest grade student
		Student MostProfitableStudent=findMostProfitableStudent();
		Student HighestGradesStudent=findHighestGradesStudent();
		PrintMostProfitableStudent(MostProfitableStudent);
		PrintHighestGradesStudent(HighestGradesStudent);

	}

	private void PrintHighestGradesStudent(Student highestGradesStudent) {//Print Highest Grades Student
		System.out.println("The student with the highest grade is: "+highestGradesStudent.getFirstName()+" "+highestGradesStudent.getLastName());
		System.out.println("The grade is: "+highestGradesStudent.getTotalGrade());
	}



	private void PrintMostProfitableStudent(Student mostProfitableStudent) {//find the student with the highest profit
		System.out.println("The student who brought the company the most profits is:" +mostProfitableStudent.getFirstName()+" "+mostProfitableStudent.getLastName() );
		System.out.println("The profit is: "+ mostProfitableStudent.getRevenue());
	}



	private Student findHighestGradesStudent() {//find the student with the highest grade
		Comparator<Student> StudntByGradesComparator= new StudntByGradesComparator();
		this.students.sort(StudntByGradesComparator);
		return this.students.elementAt(this.students.size()-1);//need to check if the sort is from low to high or the opposit
	}



	private Student findMostProfitableStudent() {//find the Most Profitable Student in the vector
		Comparator<Student> StudntByRevenueComparator= new StudntByRevenueComparator();
		this.students.sort(StudntByRevenueComparator);
		return this.students.elementAt(this.students.size()-1);//need to check if the sort is from low to high or the opposit
	}



	private Ad generateAppropriateAd(int StudentLocation) throws ThereIsNoAppripiateAdException {//generate a suitable ad for practice
		if(CheckIfSuitableAdExist(StudentLocation)) {
			int flag=0;
			int randomLocation=0;
			while (flag==0) {
				randomLocation = (int)(Math.random()*ads.size());
				try {
					if(ads.elementAt(randomLocation).suitableForStudent(students.elementAt(StudentLocation)))//if this add is suitable for this student- flag=1
						flag=1;
				}
				catch (ArrayIndexOutOfBoundsException e) {//catch the exception and do nothing
				}
			}

			return ads.elementAt(randomLocation);
		}//if

		throw new ThereIsNoAppripiateAdException();

	}



	private boolean CheckIfSuitableAdExist(int studentLocation) {//check if there is ad who is suitable for the student
		for(int i=0;i<ads.size();i++) {
			if(ads.elementAt(i).suitableForStudent(students.elementAt(studentLocation))){
				return true;
			}
		}
		return false;
	}



	private Vector<Question> CreateQuestions(Student student) {//create a set Questions for the given student
		Vector <Question> practiceQuestion=new Vector <Question>();
		Comparator<Question> QuestiontByDifficultyComparator = new QuestiontByDifficultyComparator();
		this.questions.sort(QuestiontByDifficultyComparator);
		addQuestions(student, practiceQuestion);		
		return  practiceQuestion;
	}


	private int[] findLevelRange(int level) {//return an array of the min and max index of the given level in the vector
		int[] range = new int[2];
		range[0] = FindtheMinIndex(level);
		range[1] = FindtheMaxIndex(level);
		return range;
	}



	private int FindtheMaxIndex(int level) {//find the max index of the given question level in the vector
		for(int i=0 ; i<this.questions.size();i++) {
			try {
				if(this.questions.elementAt(i).getLevel()==level&&this.questions.elementAt(i+1).getLevel()>level) 
					return i;
			}
			catch (ArrayIndexOutOfBoundsException e) {//catch the exception- do nothing

			}
		}
		return this.questions.size()-1;
	}



	private int FindtheMinIndex(int level) {//find the min index of the given question level in the vector
		for(int i=0 ; i<this.questions.size();i++) {
			if(this.questions.elementAt(i).getLevel()==level) {
				return i;
			}
		}
		return this.questions.size()-1;
	}


	private void addQuestions(Student student, Vector<Question> practiceQuestion) {//add Questions to the practice vector
		int level = student.getStudentLevel();
		int[]range= findLevelRange(level);
		int[]lowRange = findLevelRange(this.questions.elementAt(0).getLevel());//find the range of the lowest level (element at 0)
		int[]highRange = findLevelRange(this.questions.elementAt(this.questions.size()-1).getLevel());//find the range of the lowest level (element at last place)
		add4Question(range,practiceQuestion,0);//0 for english quations
		add4Question(range,practiceQuestion,1);//1 for Math quations
		add4Question(lowRange,practiceQuestion,0);
		add4Question(lowRange,practiceQuestion,1);
		add4Question(highRange,practiceQuestion,0);
		add4Question(highRange,practiceQuestion,1);
	}



	private void add4Question(int[] range, Vector<Question> practiceQuestion, int QuationType) {//count 4 questions from every level and type
		int counter = 4;
		while(counter>0) {		
			pickRandomQuationAndAtIt(range,practiceQuestion,QuationType);
			counter--;
		}
	}


	private Question pickRandomQuationAndAtIt(int[] range, Vector<Question> practiceQuestion, int quationType) {//pick random question
		int randomNumber=(int) ((Math.random() * (range[1] - range[0])) + range[0]);//generate random number in the range
		if(this.questions.elementAt(randomNumber).getQuationType()=="English"&&quationType==0) {//check if english and needed to add english
			return addEnglishQuastion(this.questions.elementAt(randomNumber),practiceQuestion,quationType,randomNumber,range);

		}
		if(this.questions.elementAt(randomNumber).getQuationType()=="Quantitative"&&quationType==1) {//check if math and needed to add math
			return addQuantitiveQuastion(this.questions.elementAt(randomNumber),practiceQuestion,quationType,randomNumber,range);

		}
		return pickRandomQuationAndAtIt(range,practiceQuestion, quationType);
	}


	private Question addQuantitiveQuastion(Question elementAt, Vector<Question> practiceQuestion, int quationType,
			int randomNumber, int[] range) {//validate the Quantitive Quastion
		if(checkIfExistInPracticeQuestion(practiceQuestion,randomNumber,this.questions.elementAt(randomNumber))) {
			return  pickRandomQuationAndAtIt(range,practiceQuestion, quationType);
		}		
		practiceQuestion.add(this.questions.elementAt(randomNumber));
		return  this.questions.elementAt(randomNumber);

	}



	private Question addEnglishQuastion(Question elementAt, Vector<Question> practiceQuestion, int quationType, int randomNumber, int[] range) {//validate the english Quastion
		if(checkIfExistInPracticeQuestion(practiceQuestion,randomNumber,this.questions.elementAt(randomNumber))) {
			return pickRandomQuationAndAtIt(range,practiceQuestion, quationType);
		}
		practiceQuestion.add(this.questions.elementAt(randomNumber));
		return this.questions.elementAt(randomNumber);

	}



	private boolean checkIfExistInPracticeQuestion(Vector<Question> practiceQuestion, int randomNumber, Question originalQuestion) {//check if the given question already exist in the practice vector 
		for(int i=0;i<practiceQuestion.size();i++) {
			if(practiceQuestion.elementAt(i).content==originalQuestion.content&&practiceQuestion.elementAt(i).choices==originalQuestion.choices) {
				return true;
			}
		}
		return false;
	}



	public static <T extends profitable> int totalRevenues (Vector <T> vector){//calculate the total revenue
		int totalRevenues=0;
		for(int i = 0 ; i < vector.size() ; i++){
			totalRevenues+=vector.elementAt(i).getprofit();
		}
		return totalRevenues;

	}

	public static <T extends Comparable<T>> T getMax (Vector <T> vector) {//return the max object by the comperator
		T max = vector.elementAt(0);
		for(int i = 0 ; i < vector.size() ; i++){		
			if(vector.elementAt(i).compareTo(max) >= 0 ){
				max = vector.elementAt(i); 
			}
		}
		return max;
	}
	public static <T extends Comparable<T>> T getMin (Vector <T> vector) {//return the min object by the comperator
		T min = vector.elementAt(0);
		for(int i = 0 ; i < vector.size() ; i++){		
			if(vector.elementAt(i).compareTo(min) <= 0 ){
				min = vector.elementAt(i); 
			}
		}
		return min;
	}

	private int findStudentLocation(String email) {//find the student location in the student vector
		for(int i=0;i<this.students.size();i++) {
			if(this.students.elementAt(i).getEmail().equals(email))
				return i;
		}
		throw new EmailIsNotExistException();
	}

	public int updateQuestionsLevel() {//update the level of the questions in the questions vector
		int updatedCounter=0;
		for(int i =0;i<this.questions.size();i++) {
			int currnetQuestionLevel=this.questions.elementAt(i).getLevel();
			this.questions.elementAt(i).updateLevel();
			int NewQuestionLevel=this.questions.elementAt(i).getLevel();
			if(currnetQuestionLevel!=NewQuestionLevel)//compare between the level before and after
				updatedCounter++;
		}
		return updatedCounter;
	}



	public Vector<Practice> getPractices() {//getter for practice vector
		return practices;
	}
}
