import java.util.*;
public class Ad implements Comparable<Ad>,profitable {
	private String content;
	private int pricePerPractice;
	private int minAge;
	private int maxAge;
	private int revenue;
	private Comparator<Ad> comperator;



	public Ad(String content,int pricePerPractice,int minAge,int maxAge ) {//constructor
		this.pricePerPractice=pricePerPractice;
		this.minAge=minAge;
		this.maxAge=maxAge;
		this.content=content;
		if(isAdultThemedAd()&&minAge<18) {
			throw new adultsOnlyExeption();
		}
		this.comperator= new AdByRevenueComparator();
	}


	private boolean isAdultThemedAd() {//check if the ad is with adult content
		Vector <String> Xvector= new  Vector <String>();
		FillxVector(Xvector);
		for(int i=0;i<this.content.length();i++){
			for(int j=0; j<Xvector.size();j++) {
				if(this.content.contains(Xvector.elementAt(j))) {
					return true;
				}
			}
		}
		return 
				false;
	}

	private void FillxVector(Vector <String> Xvector) {//fill the vector with the forbidden content
		Xvector.add("XXX");
		Xvector.add("XxX");
		Xvector.add("XXx");
		Xvector.add("xxx");
		Xvector.add("xXx");
		Xvector.add("xxX");
	}

	public boolean suitableForStudent(Student student) {//check if the ad is suitable for the student- age check
		if(student.getAge()<=this.maxAge&&student.getAge()>=this.minAge) {
			return true;
		}
		return false;

	}


	public String toString() {//to string return the ad content
		return this.content;
	}

	public int compareTo(Ad otherAd) {//compare the ad to another ad by the ad revenue
		return this.comperator.compare(this, otherAd);
	}

	public int getprofit() {//get the add profit
		return this.revenue;

	}
	public int getPricePerPractice() {//get the add profit
		return this.pricePerPractice;

	}
	public void AddToAdrofit( ) {//add the ad price per practice to the ad revenue
		this.revenue+=this.pricePerPractice;


	}



}
