package day1;

import static io.restassured.RestAssured.given;
//Static packages to be added
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {
	
	
	//Some details in the response headers will remain constant while others will change.	
	
	
	//testcase to check the header values in response
	//@Test(priority=1)
	void testHeaders()
	{
		given()
		
		.when()
			.get("https://www.google.com/")
			
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.and() //not necessary
			.header("Content-Encoding", "gzip")
			.and()//not necessary
			.header("Server", "gws");
	}
	
	
	//testcase to get all the headervalues printed
	@Test(priority=2)
	void getHeaders()
	{
		Response res = given()
		
		.when()
			.get("https://www.google.com/");
		
		//get single header info
		
		//String headervalue = res.getHeader("Content_Type");
		//System.out.println("The value of Content_Type is => "+headervalue);	
		
		
		//get all headers info.
		Headers myheaders = res.getHeaders();
		
		for(Header hd : myheaders)
		{
			System.out.println(hd.getName()+"      "+hd.getValue());
		
		}
		
		
		
			
		
	}
	
	
	
	

}
