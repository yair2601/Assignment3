import java.util.*;
public class Ad implements Comparable<Ad>,profitable {
	private String content;
	private int pricePerPractice;
	private int minAge;
	private int maxAge;
	private int revenue;
	private Comparator<Ad> comperator;


	
	public Ad(String content,int pricePerPractice,int minAge,int maxAge ) {
		this.pricePerPractice=pricePerPractice;
		this.minAge=minAge;
		this.maxAge=maxAge;
		if(isAdultThemedAd()&&minAge<18) {
			throw new adultsOnlyExeption();
		}
		this.comperator= new AdByRevenueComparator();
	}


	private boolean isAdultThemedAd() {
		int xCounter=0;
		for(int i=0;i<this.content.length();i++) {
			if(checkTheLetter(i,'x','X')) {
				xCounter++;
			}
			if(!checkTheLetter(i+1,'x','X')) {
				xCounter=0;
			}
			if(xCounter==3)return true;
		}
		return false;
	}


	private boolean checkTheLetter(int index, char char1, char char2) {
		if(this.content.charAt(index)==char1||this.content.charAt(index)==char2) {
			return true;
		}
		return false;
	}

	public boolean suitableForStudent(Student student) {
		if(student.getAge()<=this.maxAge&&student.getAge()>=this.minAge) {
			return true;
		}
		return false;
		
	}

	public int getRevenue() {
		return revenue;
	}
	public String toString() {
		return this.content;
	}


	public Comparator<Ad> getComperator() {
		return comperator;
	}


	
	public int compareTo(Ad otherAd) {
		return this.comperator.compare(this, otherAd);
	}


	
	public int getprofit() {
		return this.revenue;
		
	}
	
}
