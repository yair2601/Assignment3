import java.util.Comparator;

public class Student implements Comparable<Student>,profitable{
	private  String email;
	private String firstName;
	private String lastName;
	private int age;
	private int totalGrade;
	private int studentLevel;
	private double mathGrade;
	private double englishGrade;
	private int revenue;
	private Comparator<Student> comperator;

	public Student(String email, String firstName, String lastName, int age) {//constructor
		this.firstName=firstName;
		this.lastName=lastName;
		this.age=age;
		this.email=email;
		if (checkIfEmailIsValid(email)) {
			this.email=email;
		}
		this.comperator= new StudntByRevenueComparator();
		setLevel(age);
		
	}

	private void setLevel(int age) {
		if(age>99) {
			this.studentLevel=10;
		}if(age<0) {
			throw new ImpossibleAgeException();
		}
		this.studentLevel=(this.age/10)+1;//set the initial level of the student by his age
		
	}

	private boolean checkIfEmailIsValid(String email2) {//check if the email is valid- contain @
		if (email.contains("@"))
			return true;
		else 
			throw new ImpossibleEmailException();
	}

	public void SetTotalGrade() {//set the student total grade
		this.totalGrade=calculateTotalGrade( this.mathGrade, this.englishGrade);

	}

	private int calculateTotalGrade(double mathGrade, double englishGrade) {// calculate the student total grade according to the formula
		return  (int) ((int)700*mathGrade+300*englishGrade);
	}

	public int getRevenue() {//get the student revenue
		return revenue;
	}
	public void updateStudentLevel() {// return student level between 1-10
		int updateStudentLevel=(int)this.totalGrade/100;
		if (totalGrade==1000)
			this.studentLevel=10;
		if(updateStudentLevel==0)
			this.studentLevel=1;
		else 
			this.studentLevel= updateStudentLevel;

	}

	public int getAge() {//get the student age
		return age;
	}

	public int getStudentLevel() {//get the student level
		return studentLevel;
	}
	public String getEmail() {//get the student email
		return email;
	}

//	public Comparator<Student> getComperator() {
//		return comperator;
//	}

	public int compareTo(Student otherStudent) {//compare between two students using the revenue comparator
		
		return this.comperator.compare(this, otherStudent);
	}

	public String getFirstName() {//get the student first name
		return firstName;
	}

	public String getLastName() {//get the student last name
		return lastName;
	}
	public int getTotalGrade() {//get the student total grade
		return this.totalGrade;
	}


	public int getprofit() {//get the student profit
		return this.revenue;
	}

	public void setMathGrade(double mathGrade) {//set the student math grade
		if (mathGrade>=0 && mathGrade<=1)
			this.mathGrade = mathGrade;
	}

	public void setEnglishGrade(double englishGrade) {//set the student English grade
		if (englishGrade>=0 && englishGrade<=1)
			this.englishGrade = englishGrade;
	}
	public void AddToRevenue(int addedRevenue) {//update the student revenue in X$
		this.revenue+=addedRevenue;
	}
}
