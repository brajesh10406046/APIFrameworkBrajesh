package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import resources.APIresources;
import resources.ReUsableMethods;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.TestData;
import resources.Util;

public class stepDefinitions extends Util{

	RequestSpecification request;
	Response response;
	ReUsableMethods RM = new ReUsableMethods();
	TestData td = new TestData();
	static String place_id;
	
	
	@Given("Add Place Payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String website) throws IOException {
		request = given().spec(specBuilderRequest()).body(td.addPlacePayLoad(name, language, website));
		System.out.println("Name from AddPlace stepdef is - "+name);
		}
	
	@When("User calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String httpMethod) {
		
		APIresources resourceAPI = APIresources.valueOf(resource);
		System.out.println("resource and httpMethod got is - "+resourceAPI +httpMethod);
		String enumResource = resourceAPI.getResouces();
		System.out.println("Enum Values is - "+resourceAPI.getResouces());
		
		if(httpMethod.equalsIgnoreCase("post")) 
		{
			response = request.when().log().all().post(enumResource)
					.then().spec(specBuilderResponse()).extract().response();
		}
		else if(httpMethod.equalsIgnoreCase("get"))
		{
			response = request.when().log().all().get(enumResource)
					.then().spec(specBuilderResponse()).extract().response();
		}
	}
	
	@Then("the API call is sucess with status code {int}")
	public void the_api_call_is_sucess_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);		
	}
	
	@Then("{string} is response body {string}")
	public void is_response_body(String keyValue, String expectedValue) {
//		assertEquals(getJsonKey(response, keyValue),expectedValue);
		
	/*	String ApiResponse = response.asString();		
		JsonPath js = new JsonPath(ApiResponse);
		assertEquals(js.get(keyValue),expectedValue);*/
	}
	
	@Then("Verify that place id created maps to {string} using {string}")
	public void verify_that_place_id_created_maps_to_using(String expName, String resource) throws IOException {
		place_id = getJsonKey(response, "place_id");
		request = given().spec(specBuilderRequest()).queryParam("place_id", place_id);		
		user_calls_with_post_http_request(resource, "GET");		
		String actualName = getJsonKey(response, "name");
		assertEquals(actualName,expName);		
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("1st step def of delete called");
		request = given().spec(specBuilderRequest()).body(td.deletePlacePaylod(place_id));

	}
	
}
