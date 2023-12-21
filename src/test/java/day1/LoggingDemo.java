package day1;

import org.testng.annotations.Test;
//Static packages to be added
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class LoggingDemo {
	
	
	//testcase to show different types of logging methods
	@Test(priority=1)
	void testLogs()
	{
		
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			//.log().all();  //to log all the information
			//.log().body();  //to log just the body information
		    //.log().cookies(); // to log just the cookies information
			.log().headers();  // to log just the headers information
	}

}
