package testscripts;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class GetAndPost_ex2 {

	//first API test.
	@Test
	public void getUdersList() {
		baseURI="https://reqres.in";
		given().
		get("/api/users?page=2").
		then().
		statusCode(200).
		body("data[1].first_name",equalTo("Lindsay")).
		body("data.first_name",hasItems("Michael","Lindsay","Byron"));
	}
	
	@Test
	public void getSingleUser() {
		baseURI="https://reqres.in";
		given().get("/api/users/2").
		then().
		statusCode(200).
		body("data.first_name", equalTo("Janet"));
	}
	@Test
	public void createUser() {
		/*
		 * Map<String, Object>map =new HashMap<String,Object>(); map.put("name",
		 * "poonam"); map.put("job", "teacher"); System.out.println(map);
		 */
		JSONObject request =new JSONObject();
		request.put("name", "poonam");
		request.put("job", "teacher");
		System.out.println(request.toJSONString());
		
		baseURI="https://reqres.in";
		given().
		header("Contant-Type","application/json").
		contentType(ContentType.JSON).accept(ContentType.JSON).
		  body(request.toJSONString()).
		when().
		  post("/api/users").
		then().
		  statusCode(201).log().all();
		
		
	}
	
}
