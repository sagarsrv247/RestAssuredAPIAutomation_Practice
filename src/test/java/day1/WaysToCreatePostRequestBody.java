
/*Different Ways to create Post request body:
 1)	Post body using HashMap.
 2)Post body using org.json
 3)using POJO class
 4)using external jar file. 

 */


package day1;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

//Static packages to be added
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;	

public class WaysToCreatePostRequestBody {

	//using hashmap
	
	//@Test(priority=1)
	void testPostUsingHashmap()
	{
		HashMap data = new HashMap();
		data.put("name", "James");
		data.put("location", "China");
		data.put("phone", "99999");
		
		String courseArr[] = {"RPA", "Ruby"};
		data.put("courses", courseArr);
		
		
		given()
			.contentType("application/json").body(data)   //passing the hashmap
		
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("James"))
			.body("location", equalTo("China"))
			.body("phone", equalTo("99999"))
			//.body("courses[0]", equalTo("RPA"))
			.body("courses[1]", equalTo("Ruby"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
	

	//using org.json
	
	//@Test(priority=1)
	void testPostUsingJsonLibrary()
	{
		JSONObject data = new JSONObject();    //creating a json object
		
		data.put("name", "James");
		data.put("location", "China");
		data.put("phone", "99999");
		
		String courseArr[] = {"RPA", "Ruby"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json").body(data.toString())
		
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("James"))
			.body("location", equalTo("China"))
			.body("phone", equalTo("99999"))
			//.body("courses[0]", equalTo("RPA"))
			.body("courses[1]", equalTo("Ruby"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
	


	//using POJO
	
	//@Test(priority=1)
	void testPostUsingPOJO()
	{
		Pojo_Post_Request data = new Pojo_Post_Request();   //POJO class object with getters and setters
		data.setName("Scott");
		data.setLocation("China");
		data.setPhone("898434");
		
		String courseArr[] = {"RPA", "Ruby"};
		data.setCourses(courseArr);

		given()
			.contentType("application/json").body(data)
		
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("China"))
			.body("phone", equalTo("898434"))
			//.body("courses[0]", equalTo("RPA"))
			.body("courses[1]", equalTo("Ruby"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
	
	
	//using externaljson
	
		@Test(priority=1)
		void testPostUsingExternaJson() throws FileNotFoundException
		{
			File f = new File(".\\body.json");      
			FileReader fr = new FileReader(f);
			JSONTokener jt = new JSONTokener(fr);
			JSONObject data = new JSONObject(jt);
			
			
			given()
				.contentType("application/json").body(data.toString())
			
			.when()
				.post("http://localhost:3000/students")
				
			.then()
				.statusCode(201)
				.body("name", equalTo("King"))
				.body("location", equalTo("ALLD"))
				.body("phone", equalTo("12343443678"))
				.body("courses[0]", equalTo("Java"))
				.body("courses[1]", equalTo("X++"))
				.header("Content-Type", "application/json; charset=utf-8")
				.log().all();
		}
		
		
	
	
	
	//deleting student record
	@Test(priority=2)
	void testDelete()
	{
		 given()
		 
		 .when()
		 	.delete("http://localhost:3000/students/4")
		 	
		 .then()
		 	.statusCode(200);
		
	}
	
}
