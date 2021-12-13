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

	public Student(String email, String firstName, String lastName, int age) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.age=age;
		this.email=email;
		if (checkIfEmailIsValid(email)) {
			this.email=email;
		}
		this.comperator= new StudntByRevenueComparator();
		this.studentLevel=1;
	}

	private boolean checkIfEmailIsValid(String email2) {
		if (email.contains("@"))
			return true;
		else 
			throw new ImpossibleEmailException();
	}

	public void SetTotalGrade() {
		this.totalGrade=calculateTotalGrade( this.mathGrade, this.englishGrade);

	}

	private int calculateTotalGrade(double mathGrade, double englishGrade) {
		return  (int) ((int)700*mathGrade+300*englishGrade);
	}

	public int getRevenue() {
		return revenue;
	}
	public int updateStudentLevel() {// return student level between 1-10
		int updateStudentLevel=(int)this.totalGrade/100;
		if (totalGrade==1000)
			return 10;
		if(updateStudentLevel==0)
			return 1;
		else 
			return updateStudentLevel;

	}

	public int getAge() {
		return age;
	}

	public int getStudentLevel() {
		return studentLevel;
	}
	public String getEmail() {
		return email;
	}

	public Comparator<Student> getComperator() {
		return comperator;
	}

	public int compareTo(Student otherStudent) {
		return this.comperator.compare(this, otherStudent);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public int getTotalGrade() {
		return this.totalGrade;
	}


	public int getprofit() {
		return this.revenue;
	}

	public void setMathGrade(double mathGrade) {
		if (mathGrade>=0 && mathGrade<=1)
			this.mathGrade = mathGrade;
	}

	public void setEnglishGrade(double englishGrade) {
		if (englishGrade>=0 && englishGrade<=1)
			this.englishGrade = englishGrade;
	}
	public void AddToRevenue(int addedRevenue) {
		this.revenue+=addedRevenue;
	}
}
