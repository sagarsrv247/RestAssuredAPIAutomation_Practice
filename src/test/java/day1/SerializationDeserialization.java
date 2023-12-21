package day1;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Static packages to be added
import static io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class SerializationDeserialization {
	
	
	
	//Serialization
	@Test
	void convertPojo2Json() throws JsonProcessingException
	{
		
		//created java object using pojo class
		Pojo_Post_Request data = new Pojo_Post_Request();   //POJO class object with getters and setters
		data.setName("Scott");
		data.setLocation("China");
		data.setPhone("898434");
		
		String courseArr[] = {"RPA", "Ruby"};
		data.setCourses(courseArr);
		
		//converting java object to json object
		
		ObjectMapper objMapper =  new ObjectMapper();
		
		String jsondata = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
		
		System.out.println(jsondata);
		
	}
	
	
	//Deserialization
	@Test
	void convertJson2Pojo() throws JsonProcessingException
	{
		String jsonData = "{\r\n"
				+ "  \"name\" : \"Scott\",\r\n"
				+ "  \"location\" : \"China\",\r\n"
				+ "  \"phone\" : \"898434\",\r\n"
				+ "  \"courses\" : [ \"RPA\", \"Ruby\" ]\r\n"
				+ "}";
		
		
		//converting json data into pojo object
		ObjectMapper objMapper =  new ObjectMapper();
		Pojo_Post_Request stupojo= objMapper.readValue(jsonData, Pojo_Post_Request.class); //convert json to pojo object
		
		System.out.println("Name : "+stupojo.getName());
		System.out.println("Location : "+stupojo.getLocation());
		System.out.println("Phone : "+stupojo.getPhone());
		System.out.println("Course 1 : "+stupojo.getCourses()[0]);
		System.out.println("Course 2 : "+stupojo.getCourses()[1]);
		
		
		
		
	}

}
