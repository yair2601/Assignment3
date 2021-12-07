import java.util.*;
public class AdByRevenueComparator implements Comparator<Ad> {

	@Override
	public int compare(Ad ad1, Ad ad2) {
		int result=(ad1.getRevenue() - ad2.getRevenue());
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
