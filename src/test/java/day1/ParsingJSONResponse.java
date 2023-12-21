//3 Approaches to check the content of the Json response.


package day1;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Static packages to be added
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ParsingJSONResponse {
	
	//@Test(priority=1)
	void testJSONResponse()
	{
		/*
		//Approach 1	
			given()
				.contentType(ContentType.JSON)
			.when()
				.get("http://localhost:3000/store")
			
			.then()
				.statusCode(200)
				.header("Content-Type","application/json; charset=utf-8")
				.body("book[3].title", equalTo("The lord of the rings"));
		*/	
		
		
		//Approach 2
		
		
		Response res = given()
			.contentType("ContentType.JSON")
		
		.when()
			.get("http://localhost:3000/store");
		
		Assert.assertEquals(res.getStatusCode(),200);  //validation 1
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		String bookname = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookname, "The lord of the rings");
			
		
	}
	
	
	
	@Test(priority=2)
	void testJSONResponseBodyData()
	{
		
		Response res = given()
			.contentType(ContentType.JSON)
		
		.when()
			.get("http://localhost:3000/store");
		
		/*Assert.assertEquals(res.getStatusCode(),200);  //validation 1
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		String bookname = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookname, "The lord of the rings");*/
		
		//JSONObject Class
		
		JSONObject jo = new JSONObject(res.asString()); //converting response to json object type	
		
		//print all the titles of the book
		for(int i=0;i<jo.getJSONArray("book").length();i++)
		{
			String booktitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(booktitle);
		}
		
		
		//Search for a particular title for the book
		boolean status = false;
		for(int i=0;i<jo.getJSONArray("book").length();i++)
		{
			String booktitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(booktitle.equals("The lord of the rings"))
			{
				status = true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);
		
			
		
	}

}
