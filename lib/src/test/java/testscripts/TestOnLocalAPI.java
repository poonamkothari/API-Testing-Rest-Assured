package testscripts;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class TestOnLocalAPI {

	@Test
public void get() {
		baseURI="http://localhost:3000";
		given().get("/users").then().statusCode(200).log().all();
	
}
@Test
public void post() {
	
	JSONObject request =new JSONObject();
	request.put("firstName", "Ajay");
	request.put("lastName", "verma");
	request.put("subjectId", 1);

	System.out.println(request.toJSONString());
	
	baseURI="http://localhost:3000";
	
	given().
	contentType(ContentType.JSON).
	accept(ContentType.JSON).body(request.toJSONString())
	.when().
	post("/users").then().statusCode(201);
}
@Test
public void put() {
	JSONObject request =new JSONObject();
	request.put("firstName", "riya");
	request.put("lastName", "sharma");
	request.put("subjectId", 2);
	
	baseURI="http://localhost:3000";
	given().contentType(ContentType.JSON).
	accept(ContentType.JSON).body(request.toJSONString()).
	when().put("/users/6").then().statusCode(200);
}

}
