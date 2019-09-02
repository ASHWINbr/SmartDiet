package controller;

public class Nutrition {
	public static String nutritionValues(int age, String gender, int plan,String height, String category) {
		String calories = "";
		if (gender.equals("male")) {
			if (age >= 2 && age <= 3) {
				calories = "1060";
			} else if (age >= 4 && age <= 6) {
				calories = "1350";

			} else if (age >= 7 && age <= 9) {
				calories = "1690";

			} else if (age >= 10 && age <= 12) {
				calories = "2190";

			} else if (age >= 13 && age <= 15) {
				calories = "2750";

			} else if (age >= 16 && age <= 17) {
				calories = "3020";

			} else if (age >= 18) {
				calories = "2320";

			} else {
				System.out.println("Not under male category");
			}

		} else {

			if (age >= 2 && age <= 3) {
				calories = "1060";
			} else if (age >= 4 && age <= 6) {
				calories = "1350";

			} else if (age >= 7 && age <= 9) {
				calories = "1690";

			} else if (age >= 10 && age <= 12) {
				calories = "2190";

			} else if (age >= 13 && age <= 15) {
				calories = "2300";

			} else if (age >= 16 && age <= 17) {
				calories = "2600";

			} else if (age >= 18) {
				calories = "2100";

			} else {
				System.out.println("Not under female category");
			}

		}
		int calo=0;
		if (category.equalsIgnoreCase("Under Weight")) {
			
			if (plan==360) {
				calo=Integer.parseInt(calories)+100;;
			} else if(plan==180){
				calo=Integer.parseInt(calories)+200;;
			}else{
				calo=Integer.parseInt(calories)+300;;
			}
		} else if (category.equalsIgnoreCase("normal")){
			if (plan==360) {
				calo=Integer.parseInt(calories);
			} else if(plan==180){
				calo=Integer.parseInt(calories);
			}else{
				calo=Integer.parseInt(calories);
			}
		}else if (category.equalsIgnoreCase("Over Weight")){
			if (plan==360) {
				calo=Integer.parseInt(calories)-100;;
			} else if(plan==180){
				calo=Integer.parseInt(calories)-200;;
			}else{
				calo=Integer.parseInt(calories)-300;;
			}
			
		}else{
			if (plan==360) {
				calo=Integer.parseInt(calories)-300;;
			} else if(plan==180){
				calo=Integer.parseInt(calories)-400;;
			}else{
				calo=Integer.parseInt(calories)-500;;
			}
			
		}
		
		Double d=new Double(height);
		int heights=d.intValue();
		int cal = calo*(heights/plan);
		int total=calo+cal;
		int part=total/3;
		int part1=part-50;
		System.out.println(calo+"==============plan is==============="+plan);
		String finalCalories=part1+"@"+part+"@"+"100"+"@"+part1;
		System.out.println(cal);
		System.out.println(total);
		System.out.println(finalCalories);
		return finalCalories;
	}

}
