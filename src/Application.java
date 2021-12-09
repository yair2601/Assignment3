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
		//Vector <Question> practiceQuestion= CreateQuestions();
		Practice practice= new Practice(this.students.elementAt(StudentLocation), questions, null);
		practice.runPractice();
	}

//	private Vector<Question> CreateQuestions() {
//		Vector MathVector=createMathVector();
//		Vector EnglishVector=createEnglishVector();
//		Vector MathVectorSameLevel=createMathVectorSameLevel(MathVector);
//		Vector EnglishVectorSameLevel=createEnglishVectorSameLevel(EnglishVector);
//		Vector MathVectorHighLevel=createMathVectorHighLevel(MathVector);
//		
//		
//		return null;
//	}


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
