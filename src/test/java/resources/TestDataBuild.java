package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayLoad(String name, String language, String address) {
		AddPlace addPlace = new AddPlace();
		addPlace.setAccuracy(50);
		addPlace.setAddress(address);
		addPlace.setLanguage(language);
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setWebsite("https://www.corptut.com");
		addPlace.setName(name);
		
		List<String> typesList = new ArrayList<String>();
		typesList.add("shoe park");
		typesList.add("amanora park town");

		addPlace.setTypes(typesList);
		
		Location locObject = new Location();
		locObject.setLat(-38.383494);
		locObject.setLng(33.427362);
		addPlace.setLocation(locObject);
		return addPlace;
	}
	
	public String deletePlacePayload(String place_id) {
		return "{\r\n" + 
				"    \"place_id\":\""+place_id+"\"\r\n" + 
				"}\r\n" + 
				"";
	}
}
