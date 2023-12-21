package day1;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

//Static packages to be added
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class ParsingXMLResponse {
	
	//@Test	
	void testXMLResponse()
	{
		/*
		//Approach 1
		
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
			
		.then()
			.statusCode(200)
			.header("Content-Type","application/xml; charset=utf-8")
			.body("TravelerinformationResponse.page", equalTo("1"))
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));
	
	*/
		
		//Approach 2
		
		
			Response res = given()

			.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
			
			Assert.assertEquals(res.getStatusCode(), 200);
			Assert.assertEquals(res.header("Content-Type"),"application/xml; charset=utf-8");
			
			String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();
			Assert.assertEquals(pageNo,"1");
			
			String travelerName = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
			Assert.assertEquals(travelerName,"Developer");
	}
	
	@Test	
	void testXMLResponseBody()
	{
	
		//Approach 3
		
		
			Response res = given()
			.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
			
			XmlPath xmlobj = new XmlPath(res.asString()); //asString is used to convert response object to string
			
			List<String> travelers = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation"); //will get the list of nodes
			Assert.assertEquals(travelers.size(),10); //verify total number of travelers 
			
			
			//verify traveler name is present in the response
			
			List<String> traveler_names = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
			
			boolean status = false;
			for(String travelername:traveler_names)
			{
				if(travelername.equals("Developer"))
				{
					status = true;
					break;
				}
			}
			Assert.assertEquals(status,true);
}
}
