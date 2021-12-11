import java.util.*;
import java.io.*;

public class Application {
	private Vector<Question> questions;
	private Vector<Ad> ads;
	private Vector<Student> students;



	//Configuration = C:\Users\yair2\Desktop\Java\Configuration.txt
	public Application(String import_questions) throws IOException  {//neet to check what to throws

		this.questions= new Vector<Question>();
		this.ads= new Vector<Ad>();
		this.students= new Vector<Student>();

		importQuestions(import_questions);

	}



	private String[] createChoiceArray(String[] questionPart) {
		String[] cohiceArray = new String[4];
		for(int i=0;i<cohiceArray.length;i++ ) {
			cohiceArray[i]=questionPart[4+i];
		}
		return cohiceArray;
	}

	public void addStudent(Student s) {
		this.students.add(s);
	}
	public void addAd(Ad ad) {
		this.ads.add(ad);
	}

	public void addQuestion(Question q) {
		this.questions.add(q);
	}

	public void importQuestions(String import_questions) throws IOException {
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

	public void createPractice (String email) {
		int StudentLocation= findStudentLocation(email);
		Vector <Question> practiceQuestion= CreateQuestions(this.students.elementAt(StudentLocation));
		Practice practice= new Practice(this.students.elementAt(StudentLocation), practiceQuestion, null);
		practice.runPractice();
	}

	private Vector<Question> CreateQuestions(Student student) {
		Vector <Question> practiceQuestion=new Vector <Question>();
		Comparator<Question> QuestiontByDifficultyComparator = new QuestiontByDifficultyComparator();
		this.questions.sort(QuestiontByDifficultyComparator);
		addQuations(student, practiceQuestion);
		//		Vector MathVector=createMathVector();
		//		Vector EnglishVector=createEnglishVector();
		//		Vector MathVectorSameLevel=createMathVectorSameLevel(MathVector);
		//		Vector EnglishVectorSameLevel=createEnglishVectorSameLevel(EnglishVector);
		//		Vector MathVectorHighLevel=createMathVectorHighLevel(MathVector);
		//		
		//		
		return  practiceQuestion;
	}


	private int[] findLevelRange(int level) {
		int[] range = new int[2];
		range[0] = FindtheMinIndex(level);
		range[1] = FindtheMaxIndex(level);
		return range;
	}



	private int FindtheMaxIndex(int level) {
		for(int i=0 ; i<this.questions.size()-1;i++) {
			if(this.questions.elementAt(i).getLevel()==level&&this.questions.elementAt(i+1).getLevel()>level) {
				return i+1;
			}
		}
		return 0;
	}



	private int FindtheMinIndex(int level) {
		for(int i=0 ; i<this.questions.size();i++) {
			if(this.questions.elementAt(i).getLevel()==level) {
				return i;
			}
		}
		return this.questions.size()-1;
	}



	private void addQuations(Student student, Vector<Question> practiceQuestion) {
		int level = student.getStudentLevel();
		int[]range= findLevelRange(level);
		int[]lowRange = findLevelRange(this.questions.elementAt(0).getLevel());//find the range of the lowest level (element at 0)
		int[]highRange = findLevelRange(this.questions.elementAt(this.questions.size()-1).getLevel());//find the range of the lowest level (element at last place)
		highRange[1]=this.questions.size()-1;//repair for high range
		add4Question(range,practiceQuestion,0);//english quations
		add4Question(range,practiceQuestion,1);//Math quations
		add4Question(lowRange,practiceQuestion,0);
		add4Question(lowRange,practiceQuestion,1);
		add4Question(highRange,practiceQuestion,0);
		add4Question(highRange,practiceQuestion,1);
	}



	private void add4Question(int[] range, Vector<Question> practiceQuestion, int QuationType) {
		int counter = 4;
		while(counter>0) {		
			pickRandomQuationAndAtIt(range,practiceQuestion,QuationType);
			counter--;
		}
}


private Question pickRandomQuationAndAtIt(int[] range, Vector<Question> practiceQuestion, int quationType) {
	int randomNumber=(int) ((Math.random() * (range[1] - range[0])) + range[0]);//generate random number in the range
	if(this.questions.elementAt(randomNumber).getQuationType()=="English"&&quationType==0) {//check if english and needed to add english
		if(checkIfExistInPracticeQuestion(practiceQuestion,randomNumber,this.questions.elementAt(randomNumber))) {
			pickRandomQuationAndAtIt(range,practiceQuestion, quationType);
		}
		practiceQuestion.add(this.questions.elementAt(randomNumber));
		return this.questions.elementAt(randomNumber);
	}
	if(this.questions.elementAt(randomNumber).getQuationType()=="Quantitative"&&quationType==1) {//check if math and needed to add math
		if(checkIfExistInPracticeQuestion(practiceQuestion,randomNumber,this.questions.elementAt(randomNumber))) {
			pickRandomQuationAndAtIt(range,practiceQuestion, quationType);
		}		
		practiceQuestion.add(this.questions.elementAt(randomNumber));
		return this.questions.elementAt(randomNumber);
	}
	 return pickRandomQuationAndAtIt(range,practiceQuestion, quationType);
	
}



private boolean checkIfExistInPracticeQuestion(Vector<Question> practiceQuestion, int randomNumber, Question originalQuestion) {
	for(int i=0;i<practiceQuestion.size();i++) {
		if(practiceQuestion.elementAt(i).content==originalQuestion.content&&practiceQuestion.elementAt(i).choices==originalQuestion.choices) {
			return true;
		}
	}
		return false;
}



//
//	private Vector<Question> createMathVector() {
//		Object result = this.questions.clone();
//		
//		for(int i=0;i<result.size();i++) {
//			result.elementAt(i)=questions.elementAt(i);
//			
//		}
//		return result;
//	}



public static <T extends Comparable<T>> T getMax (Vector <T> vector) {
	T max = vector.elementAt(0);
	for(int i = 0 ; i < vector.size() ; i++){		
		if(vector.elementAt(i).compareTo(max) >= 0 ){
			max = vector.elementAt(i); 
		}
	}
	return max;


}
public static <T extends Comparable<T>> T getMin (Vector <T> vector) {
	T min = vector.elementAt(0);
	for(int i = 0 ; i < vector.size() ; i++){		
		if(vector.elementAt(i).compareTo(min) <= 0 ){
			min = vector.elementAt(i); 
		}
	}
	return min;


}

private int findStudentLocation(String email) {
	for(int i=0;i<this.students.size();i++) {
		if(this.students.elementAt(i).getEmail().equals(email))
			return i;
	}
	throw new EmailIsNotExistException();
}

}
