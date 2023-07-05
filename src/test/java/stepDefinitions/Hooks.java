package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@AddPlaceAPI")
	public void beforeScenario() throws IOException {
		
		stepDefinitions SD = new stepDefinitions();
		if(stepDefinitions.place_id==null) {
			
		SD.add_place_payload("Rajesh", "Spanish", "Zudiac.com");
		SD.user_calls_with_post_http_request("addPlaceAPI", "POST");
		SD.verify_that_place_id_created_maps_to_using("ABC", "GET");
		}		
	}

}
