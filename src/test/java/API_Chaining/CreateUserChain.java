package API_Chaining;
//Static packages to be added
import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


public class CreateUserChain {
	
	
	@Test
	void test_createUser(ITestContext context)
	{
		
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender","Male");
		data.put("email",faker.internet().emailAddress());
		data.put("status", "inactive");
		
		String bearerToken = "6040e59d86456774a3fac3d8bae47ccde4033c9a3c7b1b8fcf99d27cb70eba78";
		
		
		int id= given()
			.headers("Authorization", "Bearer "+bearerToken)
			.contentType("application/json")
			.body(data.toString())
			
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
			
		System.out.println("Generated ID is "+id);
		context.setAttribute("user_id", id);
		
	}

}