package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
	
		// if place id is null, then create place id
		StepDefinition stepDef = new StepDefinition();
		if(StepDefinition.place_id == null) {
			stepDef.add_Place_Payload_with("Atul", "German", "India");
			stepDef.user_calls_with_http_request("AddPlaceAPI", "POST");
			stepDef.verify_place_id_created_maps_to_using("Atul", "getPlaceAPI");	
		}
	}
}
