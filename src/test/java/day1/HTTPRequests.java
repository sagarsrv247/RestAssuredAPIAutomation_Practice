//Project to check the HTTPs requests 


//given is used for all the preconditions before sending the request
//when is used for the type of the request to be sent
//then is used for comparing the values
//all the functions are written in TestNG


package day1;

import org.testng.annotations.Test;
//Static packages to be added
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
//


public class HTTPRequests {
	
	int id;
	
	@Test(priority=1)
	void getUsers()
	{
		given()
		
		
		.when()
			.get("https://reqres.in/api/users?page=2")      //get request
		
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();					 //to print all the messages from the response in the console
			
	}
	
	
	@Test(priority=2)
	void CreateUser()
	{
		HashMap data = new HashMap();   //hashmap to store the data as key value pairs as in JSON
		data.put("name","sagar");
		data.put("job", "qa");
		
		id = given()							//id is used to store the id of the posted request
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("https://reqres.in/api/users")      //post request
			.jsonPath().getInt("id");
			
			
		//.then()
		//	.statusCode(201)
		//	.log().all();
		
	}
	
	
	@Test(priority=3, dependsOnMethods= {"CreateUser"})
	void UpdateUser()
	{
		HashMap data = new HashMap();
		data.put("name","Sagar");
		data.put("job", "sdet");
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.put("https://reqres.in/api/users/"+id)    //put request with the id used above

			
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	
	@Test(priority=4, dependsOnMethods= {"UpdateUser"})
	void DeleteUser()
	{
		given()
		.when()
			.delete("https://reqres.in/api/users/"+id)   //delete request with the id used above
		.then()
			.statusCode(204)
			.log().all();
		
		
	}
	
	
}





/*
GET:  https://reqres.in/api/users?page=2

POST:
	https://reqres.in/api/users
	{
	    "name": "morpheus",
	    "job": "leader"
	}
	
PUT:

https://reqres.in/api/users/2
	{
    "name": "morpheus",
    "job": "zion resident"
}


DELETE:

https://reqres.in/api/users/2



*/