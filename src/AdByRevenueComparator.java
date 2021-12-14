import java.util.*;
public class AdByRevenueComparator implements Comparator<Ad> {

	public int compare(Ad ad1, Ad ad2) {//compare two ad by their revenues
		int result=(ad1.getprofit() - ad2.getprofit());
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
