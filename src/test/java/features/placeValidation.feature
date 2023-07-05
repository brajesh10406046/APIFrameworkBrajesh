Feature: Validation place API's
@AddPlaceAPI @Regression
Scenario Outline: Verify if place is successfully added in teh API
	Given Add Place Payload "<Name>" "<Language>" "<Website>"
	When User calls "getPlaceAPI" with "post" http request
	Then the API call is sucess with status code 200
	And "status" is response body "OK"
	And "scope" is response body "APP"
	And Verify that place id created maps to "<Name>" using "getPlaceAPI"
	
	Examples:
	|Name			|	Language	|	Website						|
	|Neha			|	Bengali		|	findyourfasion.com|
#	|Brajesh	|	Hindi			|	brajeshvolvo.com	|
	
@DeletePlaceAPI @Regression
	Scenario: Verify that delete place API is working
	Given DeletePlace Payload
	When User calls "deletePlaceAPI" with "post" http request
	Then the API call is sucess with status code 200
	And "status" is response body "OK"