import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;



import Files.Payload;
import io.restassured.path.json.JsonPath;

public class MockJsonTest {
	
	@Test
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js = new JsonPath(Payload.CoursePrice());

		//print no. of courses
		int courseCount= js.getInt("courses.size()");
		System.out.println("no. of course = "+ courseCount);
		
		//print purchase amount
		int purchasedAmount = js.getInt("dashboard.purchasedAmount");
		System.out.println("Total purchased amount = "+ purchasedAmount);
		
		//print title of the first course
		String firstCourseTitle = js.getString("courses[0].title");
		System.out.println("First Course Title = " + firstCourseTitle);
		
		//print all course titles and their respective prices
		int count= js.getInt("courses.size()");
		for(int i=0; i<count; i++) {
			String title = js.getString("courses["+i+"].title");
			String coursePrice = js.getString("courses["+i+"].price");	
			System.out.println(title + " = " + coursePrice);
			}
		
		//print no. of copies sold by RPA course
		int count1= js.getInt("courses.size()");
		
		for(int i=0; i<count1; i++) {
			String courseTitle = js.get("courses["+i+"].title");
			
			if(courseTitle.equalsIgnoreCase("RPA")) {
				int copiesRPA = js.get("courses["+i+"].copies");
				System.out.println("no. of RPA copies sold = " + copiesRPA);
				break;
			}
		}
		
		//check if sum of all courses equals to Purchase Amount
		int count2 = js.getInt("courses.size()");
		int sum = 0;
		for(int i=0; i<count2; i++) {
			int coursePrice = js.get("courses["+i+"].price");
			int courseCopies = js.get("courses["+i+"].copies");
			
			int Amount = coursePrice * courseCopies ;
			sum = sum + Amount;
		}
		int purchaseAmount = js.getInt("dashboard.purchasedAmount");
		Assert.assertEquals(sum, purchaseAmount);
		System.out.println("sum = " + sum + " purchase amount = " + purchaseAmount);
		
			
		
	}

}
