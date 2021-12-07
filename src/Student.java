
public class Student {
	private  String email;
	private String firstName;
	private String lastName;
	private int age;
	private int totalGrade;
	private int studentLevel;
	private int mathGrade;
	private int englishGrade;
	private int revenue;

	public Student(String email, String firstName, String lastName, int age) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.age=age;
		if (checkIfEmailIsValid(email)) {
			this.email=email;
		}
	}

	private boolean checkIfEmailIsValid(String email2) {
		if (email.contains("@"))
			return true;
		else 
			throw new ImpossibleEmailException();
	}
	
	public void updateGrade(int mathGrade, int englishGrade) {
		this.totalGrade=calculateTotalGrade( mathGrade, englishGrade);
		
	}

	private int calculateTotalGrade(int mathGrade, int englishGrade) {
		return 700*mathGrade+300*englishGrade;
	}

	public int getRevenue() {
		return revenue;
	}
	
}
