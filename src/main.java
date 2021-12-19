import java.util.Vector;

public class main {

	public static void main(String[] args) throws Exception {
		try{
			Application app1 = new Application("invlaid_level_-1");
		}catch(ImpossibleQuestionLevelException e) {
			System.out.println("invlaid_level_-1");
		}
		try{
			Application app2 = new Application("invlaid_level_11");
		}catch(ImpossibleQuestionLevelException e) {
			System.out.println("invlaid_level_11");
		}
		try{
			Application app3 = new Application("noExistFile");
		}catch(NullPointerException e) {
			System.out.println("notExistFile");
		}
		try{
			Application app3 = new Application("Questions_1");
		}catch(NullPointerException e) {
			System.out.println("we have a problem that was good one");
		}
		Application app = new Application("Questions_1");
		Student Yair = new Student("yair@", "Yair", "Gafsou", 24);
		Student Nir = new Student("nir@", "Nir", "Tal", 13);
		try{
			Student Lihi = new Student("lihi", "Nir", "Tal", 25);
		}catch(ImpossibleEmailException e) {
			System.out.println("this is not a good email");
		}
		Student Lihi = new Student("lihi@fsdfsdf156", "lihi", "D", 5);
		Student Lian = new Student("@", "Lian", "t", 95);
		
		app.addStudent(Yair);
		app.addStudent(Nir);
		app.addStudent(Lian);
		app.addStudent(Lihi);
		//app.addStudent(Yair);//need to check if we can insert the same Student
		if(Lian.getRevenue()!=10)System.out.println("we have problem the initial revenue isnt10");
		Ad ad1= new Ad("ad1", 45, 0, 110);
		try{
			Ad ad2= new Ad("ad2xxx", 55, 15, 17);
		}catch(adultsOnlyExeption e){
			System.out.println("bad ad");
		}
		Ad ad3= new Ad("ad3XXX", 65, 18, 100);
		Ad ad4= new Ad("adXxX3", 65, 18, 30);
		Ad ad5= new Ad("adXXx", 65, 18, 30);
		Ad ad6= new Ad("adXXx", 65, 18, 30);
		Ad ad7= new Ad("XXxadXXx", 65, 18, 30);
		try{
			Ad ad8= new Ad("XXxad", 65, 15, 30);
		}catch(adultsOnlyExeption e){
			System.out.println("bad ad");
		}
		//Ad ad9 = new Ad(null, -2, 1, 15);
		try{
			Ad ad10= new Ad("xXXad", 65, 15, 17);
		}catch(adultsOnlyExeption e){
			System.out.println("bad ad");
		}
		app.addAd(ad3);
		app.addAd(ad1);
		app.addAd(ad4);
		app.addAd(ad5);
		app.addAd(ad6);
		app.addAd(ad7);
		//app.addAd(ad9);

		String [] choices= new String[4];
		choices [0]="aaaa";
		choices [1]="bbbb";
		choices [2]="cccc";
		choices [3]="dddd";
		char sign='b';
		try {
			Question ques11= new QuantitativeQuestion("nir question",11,sign,choices,"542formula");
		}catch(ImpossibleQuestionLevelException e) {
			System.out.println("high level Question");
		}
		try {
			Question ques0= new QuantitativeQuestion("nir question",0,sign,choices,"542formula");
		}catch(ImpossibleQuestionLevelException e) {
			System.out.println("high level Question");
		}
		Question ques= new QuantitativeQuestion("nir question",8,sign,choices,"542formula");
		app.addQuestion(ques);
		app.importQuestions("goodFile");
	
//		for (int i = 0; i < 100; i++) {
//            int rand = (int)(Math.random() * 10)+1 ;
//            System.out.println(rand);
//		}
//		while (true) {
//			System.out.println(Math.random()*10);
//		}
		
		//boolean flag=ad4.isAdultThemedAd();
		//boolean flag2=ad3.isAdultThemedAd();
		app.createPractice("nir@");
		app.createPractice("nir@");
		app.createPractice("nir@");
		app.createPractice("@");
		app.createPractice("lihi@fsdfsdf156");
		try{
			app.createPractice("shmulik");
		}catch(EmailIsNotExistException e){
			System.out.println("this email does not exist");
		}
		System.out.println(Nir.getRevenue());
		app.bestStudents();
		
		for(int i =0; i<app.getPractices().size();i++) {//count how many q for every level
			int same=0,high=0,low= 0;
			for(int j=0;j<app.getPractices().elementAt(i).getQuestions().size();j++) {
				if(app.getPractices().elementAt(i).getQuestions().elementAt(j).getLevel()<app.getPractices().elementAt(i).getStudent().getStudentLevel()) {
					low++;
				}
				if(app.getPractices().elementAt(i).getQuestions().elementAt(j).getLevel()==app.getPractices().elementAt(i).getStudent().getStudentLevel()) {
					same++;
				}
				if(app.getPractices().elementAt(i).getQuestions().elementAt(j).getLevel()>app.getPractices().elementAt(i).getStudent().getStudentLevel()) {
					high++;
				}
			}
			System.out.print(same + "\t");
			System.out.print(low+"\t");
			System.out.print(high+"\t");
			System.out.println();
		}
		System.out.println(app.updateQuestionsLevel());
		
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
