package API_Chaining;


//Static packages to be added
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;


public class GetUserChain {
	
	@Test
	void test_getUser(ITestContext context)
	{
		
		int id=(Integer) context.getAttribute("user_id"); //this should come from create user class
		
		
		String bearerToken = "6040e59d86456774a3fac3d8bae47ccde4033c9a3c7b1b8fcf99d27cb70eba78";
		
		
		given()
		.headers("Authorization", "Bearer "+bearerToken)
		.pathParam("id", id)
		
		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
			.statusCode(200)
			.log().all();
			
		
	}

}
