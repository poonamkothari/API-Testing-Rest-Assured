package testscripts;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestAPIClass {
	@Test
	public void getUsersInPageTwo() {
		Response response =given().when().get("https://reqres.in/api/users?page=2");
		int actualStatucsCode=response.statusCode();
		Assert.assertEquals(actualStatucsCode, 200);
		System.out.println("response code is " +actualStatucsCode);
	}
	
	@Test
	public void getSingleUser() {
		Response response =given().when().get("https://reqres.in/api/users/2");
		int actualStatus = response.getStatusCode();
		System.out.println("status code is: "+actualStatus);
		ResponseBody responseBody = response.getBody();
		String actualResponseBody = responseBody.asString();
		Assert.assertTrue(actualResponseBody.contains("Janet"));
	}
	@Test
	public void singleResouuce() {
		Response response = RestAssured.get("https://reqres.in/api/unknown/2");
		//Response response =get("https://reqres.in/api/unknown/2"); after importing rest assured in static way.
		int stausCode = response.getStatusCode();
		System.out.println(stausCode);
		System.out.println(response.getTime());
		
		Assert.assertEquals(stausCode, 200);
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("content-type"));	
	}
	@Test
	public void resourcesList() {
		baseURI="https://reqres.in";
		given().
		get("/api/unknown").
		then().
		statusCode(200).body("data[3].id", equalTo(4)).
		log().all();
		
	}

}
