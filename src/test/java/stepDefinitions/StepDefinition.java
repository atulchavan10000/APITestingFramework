package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.junit.Assert.*;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	
	

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
			
		reqSpec = given()
			.spec(requestSpecification())
			.body(data.addPlacePayLoad(name, language, address));
	}



	@When("user calls {string} with {string} http request")
	public void user_calls_with_Post_http_request(String resource, String httpMethod) {
		/** APIResources constructor will be loaded with value of String resource
		*String resource will receive a  string from a feature file
		**/
		APIResources resourceAPI = APIResources.valueOf(resource);
		
		resSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		if(httpMethod.equalsIgnoreCase("POST")) {
			response = reqSpec.when().post(resourceAPI.getResource());		
		}else if(httpMethod.equalsIgnoreCase("GET")){
			response = reqSpec.when().get(resourceAPI.getResource());		
		}

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
