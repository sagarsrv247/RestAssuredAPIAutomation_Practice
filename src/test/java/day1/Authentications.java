package day1;
import org.testng.annotations.Test;

import io.restassured.response.Response;

//Static packages to be added
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class Authentications {

	@Test(priority=1)
	void testBasicAuthentication()
	{
		given()
			.auth().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test(priority=2)
	void testDigestAuthentication()
	{
		given()
			.auth().digest("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}	
	
	
	@Test(priority=3)
	void testPreemptiveAuthentication()
	{
		given()
			.auth().preemptive().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	@Test(priority=4)
	void testbearerAuthentication()
	{
		
		String bearerToken = "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";
		given()
			.headers("Authorization","Bearer "+bearerToken)
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	//syntax for oauth 1
	
	//@Test
	void testoauth1Authentication()
	{
		given()
			.auth().oauth("consumer key", "consumer secret", "access token", "token secret")
			
		.when()
			.get("url")
			
		.then()
			.statusCode(200)
			.log().all();


	}
	
	
	//syntax for oauth 2
	
	//@Test
	void testoauth2Authentication()
	{
		given()
			.auth().oauth2	("access token")
			
		.when()
			.get("url")
			
		.then()
			.statusCode(200)
			.log().all();


	}
	
	//syntax for api key authentication
	
	//@Test
	void testapikeyAuthentication()
	{
		given()
			.queryParam("keyname", "keyvalue")
			
		.when()
			.get("url")
			
		.then()
			.statusCode(200)
			.log().all();


	}
	
	
	
	
	
	

}
