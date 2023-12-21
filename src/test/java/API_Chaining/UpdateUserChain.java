package API_Chaining;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUserChain {
	
	@Test
	void test_updateUser(ITestContext context)
	{

		
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender","Male");
		data.put("email",faker.internet().emailAddress());
		data.put("status", "active");
		
		String bearerToken = "6040e59d86456774a3fac3d8bae47ccde4033c9a3c7b1b8fcf99d27cb70eba78";
		
		
		int id=(Integer) context.getAttribute("user_id");
		
		given()
			.headers("Authorization", "Bearer "+bearerToken)
			.contentType("application/json")
			.pathParam("id", id)	
			.body(data.toString())
			
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
		
			
			.then()
				.statusCode(200)
				.log().all();
	}

}
