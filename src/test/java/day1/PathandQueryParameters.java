package day1;

import org.testng.annotations.Test;
//Static packages to be added
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathandQueryParameters {
	
	
	
	//test case to show the use of path and query parameters
	@Test
	void testQueryAndPathParameters()
	{
		
		given()
			.pathParam("mypath1","api")//path parameters
			.pathParam("mypath2","users") //path parameters
			.queryParam("page",2) //query parameters
			.queryParam("id",5)//query parameters
			
			
		.when()
			.get("https://reqres.in/{mypath1}/{mypath2}")
			//query parameters will go with the request automatically.
		
		.then()
			.statusCode(200)
			.log().all();
		
	}

}
