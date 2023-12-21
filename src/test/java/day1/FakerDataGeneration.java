package day1;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGeneration {
	
	
	@Test
	void testgeneratedummydata()
	{
		Faker faker = new Faker();
		String fullname = faker.name().fullName();
		String firstname = faker.name().firstName();
		String lastname = faker.name().lastName();
		String username = faker.name().username();
		String password = faker.internet().password();
		String phone = faker.phoneNumber().cellPhone();
		String email = faker.internet().safeEmailAddress();
		
		System.out.println("fullname: "+fullname);
		System.out.println("firstname: "+firstname);
		System.out.println("lastname: "+lastname);
		System.out.println("username: "+username);
		System.out.println("password: "+password);
		System.out.println("phone: "+phone);
		System.out.println("email: "+email);
	}

}
	