import java.util.Vector;

public class main {

	public static void main(String[] args) throws Exception {
		Application app = new Application("Questions_1");
		Student Yair = new Student("yair@", "Yair", "Gafsou", 24);
		Student Nir = new Student("nir@", "Nir", "Tal", 25);
		Student Nir2 = new Student("nir@", "Nir2", "Tal2", 25);
		app.addStudent(Yair);
		app.addStudent(Nir);
		app.addStudent(Nir2);
		
		Ad ad1= new Ad("ad1", 45, 0, 110);
		Ad ad2= new Ad("ad2", 55, 15, 17);
		Ad ad3= new Ad("ad3", 65, 18, 30);
		Ad ad4= new Ad("adXxX3", 65, 18, 30);
		app.addAd(ad3);
		app.addAd(ad2);
		app.addAd(ad1);
		app.addAd(ad4);
		String [] choices= new String[4];
		choices [0]="aaaa";
		choices [1]="bbbb";
		choices [2]="cccc";
		choices [3]="dddd";
		char sign='b';
		Question ques= new QuantitativeQuestion("nir question",2,sign,choices,"542formula");
		//app.addQuestion(ques);
	
//		for (int i = 0; i < 100; i++) {
//            int rand = (int)(Math.random() * 10)+1 ;
//            System.out.println(rand);
//		}
//		while (true) {
//			System.out.println(Math.random()*10);
//		}
		
//		boolean flag=ad4.isAdultThemedAd();
//		boolean flag2=ad3.isAdultThemedAd();
		app.createPractice("nir@");
		app.createPractice("nir@");
		app.createPractice("nir@");
		app.createPractice("yair@");
		app.createPractice("yair@");
		System.out.println(Yair.getRevenue());
		app.bestStudents();
		//System.out.println(app.updateQuestionsLevel());
		
//		String str = "965	Robust systematic matrices	9	c	Vanessa indica	Certotrichas paena	Papio cynocephalus	Varanus sp.		user-facing\r\n";
//		String[] newstr= new String[10];
//		newstr = str.split("\t");
//		System.out.println(newstr[10]);
//		Vector a= new Vector();
//		a.add(2);
//		a.add("e");
//		a.add(5);
//		System.out.println(a.elementAt(0));
//		System.out.println(a.elementAt(1).getClass());
	}

}
