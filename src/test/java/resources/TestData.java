package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestData {

	public AddPlace addPlacePayLoad(String name, String language, String website ) {
		
		//Creating object of Addplace class to use all data
		AddPlace ap = new AddPlace();

		//Creating List type for "types" data
		List<String> typesData = new ArrayList<String>();
		typesData.add("shoe park");
		typesData.add("shop");
		

		//Object for locationto set "lang" and "lat"
		Location locData = new Location();
		locData.setLang(33.427362);
		locData.setLat(-38.383494);
		
		
		ap.setAccuracy(50);
		ap.setName(name);
		ap.setPhone_number(9999);
		ap.setAddress("Brajesh Address, Greater Noida");
		ap.setTypes(typesData);		
		ap.setWebsite(website);		
		ap.setLanguage(language);
		ap.setLocation(locData);
		
		return ap;
	}
	
	public String deletePlacePaylod(String placeID) {
		
		return "{\"place_id\":\""+placeID+"\"}";
	}
	

}
