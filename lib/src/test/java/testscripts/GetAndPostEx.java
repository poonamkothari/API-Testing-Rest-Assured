package testscripts;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetAndPostEx {
	
	@Test
	public void getTest() {

		baseURI ="https://reqres.in";
		given().get("/api/users?page=2").
		then().
		statusCode(200).body("data[4].first_name",equalTo("George")).
		body("data.first_name", hasItems("Michael","Lindsay","George"));	
	}
	@Test
	public void testPost() {
		
		//first we have to create body
		Map<String, Object> map =new HashMap<String,Object>();
		//map.put("name", "poonam");
		//map.put("job", "teacher");
		//System.out.println(map);
		JSONObject request =new JSONObject();
		request.put("name", "poonam");
		request.put("job", "teacher");
		System.out.println(request.toJSONString());
		baseURI="https://reqres.in"; 
		
		given().
		headers("Content-Type","application/json").
		contentType(ContentType.JSON).accept(ContentType.JSON).
		body(request.toJSONString()).
		when().
		post("/api/users").then().statusCode(201).
		log().all();
		
	}
	
	

}
