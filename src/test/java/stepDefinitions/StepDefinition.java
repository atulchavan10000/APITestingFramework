package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.junit.Assert.*;
import pojo.AddPlace;
import pojo.Location;
import resources.TestDataBuild;

public class StepDefinition {
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add Place Payload")
	public void add_Place_Payload() {
	    // Write code here that turns the phrase above into concrete actions
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		

		
		
		RequestSpecification requestSpecificationToMerge =  new RequestSpecBuilder()
			.setBaseUri("https://rahulshettyacademy.com")
			.addQueryParam("key", "qaclick123")
			.setContentType(ContentType.JSON)
			.build();
		
		
		resSpec = new ResponseSpecBuilder()
			.expectStatusCode(200)
			.expectContentType(ContentType.JSON).build();
			
		reqSpec = given()
			.spec(requestSpecificationToMerge)
			.body(data.addPlacePayLoad());
	}



	@When("user calls {string} with Post http request")
	public void user_calls_with_Post_http_request(String string) {
		response = reqSpec.when()
				.post("/maps/api/place/add/json")
			.then()
				.spec(resSpec)
				.extract().response();
	}
	@Then("the API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) {
	    assertEquals(response.getStatusCode(), 200);			
	}
	
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		String resp= response.asString();
		JsonPath js = new JsonPath(resp);
		assertEquals(js.get(keyValue).toString(), expectedValue);
	}
	

}
