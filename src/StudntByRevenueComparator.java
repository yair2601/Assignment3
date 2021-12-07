import java.util.Comparator;

public class StudntByRevenueComparator implements Comparator<Student> {

	public int compare(Student Student1, Student Studnet2) {
		int result=(Student1.getRevenue() - Studnet2.getRevenue());
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
