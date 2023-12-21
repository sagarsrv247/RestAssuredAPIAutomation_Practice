package day1;

import org.testng.annotations.Test;

import io.restassured.response.Response;

//Static packages to be added
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class CookiesDemo {
	
	
	
	//to test whether the cookie value is changing or not, if it is changing this test case will fail
	//@Test(priority=1)
	void testCookies()
	{
		given()
		
		.when()
			.get("https://www.google.com/")
			
		.then()
			.cookie("AEC", "Ackid1QBOHtjgiCLxw_VjvIsSYMcSQQzg5NezzkPii0Uve5cE1WxPVQcOEA")
			//cookie value will change after every run, if the cookie value is changing it means the test is passing else failing.
			.log().all();
		
	}
	
	
	
	
	
	//to get single and all cookie names and values.
	
	@Test(priority=2)
	void getCookiesInfo()
	{
		Response res = given()
		
		.when()
			.get("https://www.google.com/");
		
		//get single cookie info
		//String cookie_value = res.getCookie("AEC"); 
		//System.out.println("Value of cookie is ==> "+cookie_value);
		
		//get all cookies info
		Map<String, String >cookie_values = res.getCookies();
		//System.out.println(cookie_values.keySet());
		for(String k: cookie_values.keySet())
		{
			String cookie_value = res.getCookie(k);
			System.out.println(k+"   "+cookie_value);
		}
		
		
				
		
	}


}
