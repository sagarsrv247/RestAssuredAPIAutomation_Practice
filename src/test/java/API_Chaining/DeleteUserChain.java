package API_Chaining;

//Static packages to be added
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUserChain {
	
	
	@Test
	void test_deleteUser(ITestContext context)
	{
		
		int id=(Integer) context.getAttribute("user_id");
		
		String bearerToken = "6040e59d86456774a3fac3d8bae47ccde4033c9a3c7b1b8fcf99d27cb70eba78";
		
		given()
			.headers("Authorization", "Bearer "+bearerToken)
			.pathParam("id", id)
		
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
			
		.then()
			.statusCode(204)
			.log().all();
	
	}
	

}
