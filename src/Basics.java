import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import java.nio.file.Files;

import Files.Payload;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String oldAddress = "c-14, address 1, added for practise, by yash";
		String newAddress = "B-13, address 2, added for practise, by Venkat";
		
		//base URI
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		
		//Add Place
		String Response = given()
			.queryParam("key", "qaclick123")
			.headers("content-Type", "application/json")
			.body(Payload.AddPlace(oldAddress))
			.when()
				.post("/maps/api/place/add/json")
			.then().assertThat()
				.statusCode(200)
				.body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.52 (Ubuntu)")
				.extract().response().asString();
		
		JsonPath js = new JsonPath(Response);
		String place_id = js.getString("place_id");
		System.out.println("Place Id ::: " + place_id);
		
		
		//Get Place
		String getResponse = given()
			.queryParam("key", "qaclick123")
			.queryParam("place_id", place_id)
		.when()
			.get("/maps/api/place/get/json")
		.then().assertThat()
			.statusCode(200)
			.extract().response().asString();
		
		JsonPath getAddress = new JsonPath(getResponse);
		String actualAddress = getAddress.getString("address");
		Assert.assertEquals(actualAddress, oldAddress);
		System.out.println("Address 1 ::: " + actualAddress);
		
		
		//Update Place
		
		String UpdatedResponse = given()
			.queryParam("key", "qaclick123")
			.queryParam("place_id", place_id)
			.headers("content-Type", "application/json")
			.body(Payload.UpdatePlace(place_id, newAddress))
		.when()
			.put("/maps/api/place/update/json")
		.then().assertThat()
			.statusCode(200)
			.body("msg", equalTo("Address successfully updated"))
			.extract().response().asString();
		System.out.println("Updated Response ::: " + UpdatedResponse);
		
		
		//Get Updated Place
				String getResponse2 = given()
					.queryParam("key", "qaclick123")
					.queryParam("place_id", place_id)
				.when()
					.get("/maps/api/place/get/json")
				.then().assertThat()
					.statusCode(200)
					.body("address", equalTo(newAddress))
					.extract().response().asString();
				
				JsonPath getAddress2 = new JsonPath(getResponse2);
				String actualAddress2 = getAddress2.getString("address");
				Assert.assertEquals(actualAddress2, newAddress);
				System.out.println("Address 2 ::: " + actualAddress2);	

				//master changes
				System.out.println("Yashvanth made this change 1 in Eclipse Editor");
				System.out.println("Yashvanth made this change 2 in Eclipse Editor");				
		
				
				//git chanes
				System.out.println("Yashvanth made this change 1 in Git");
				System.out.println("Yashvanth made this change 2 in Git");
				
				
				//develop branch cahnges
				System.out.println("new chenges 1 in unknown branch mostly develop");
				System.out.println("new changes 2 in develop branch");
				System.out.println("new changes 3 in develop branch");
				
				
	}

}
