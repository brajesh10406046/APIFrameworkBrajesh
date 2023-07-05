package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Util {
	public static RequestSpecification req;
	public RequestSpecification specBuilderRequest() throws IOException {
		
		
		if(req==null) {
		PrintStream log = new PrintStream(new FileOutputStream("logData.txt"));
		req = new RequestSpecBuilder().setContentType(ContentType.JSON)
				.setBaseUri(getGlobalProperties("baseURL"))
				.addQueryParam("key", "qaclick123")
				.addQueryParam("Content-Type", "application/json")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return req;
		}
		return req;
	}
	
	
	public ResponseSpecification specBuilderResponse() {
		
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		return res;
	}
	
	public String getGlobalProperties(String Key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\braje\\API Training Projects\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(Key);		
		
	}
	
	public String getJsonKey(Response res, String Key) {
		String resp = res.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(Key).toString();
	}

}
