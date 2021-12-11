import java.util.Comparator;

public class StudntByGradesComparator implements Comparator<Student>{
	
	public int compare(Student Student1, Student Studnet2) {
		int result=(Student1.getTotalGrade() - Studnet2.getTotalGrade());
		if(result==0) {
			return 0;
		}
		if (result>0) {
			return 1;
		}
		if (result<0) {
			return -1;
		}
		return 0;
	}
}
