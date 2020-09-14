package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification requestSpecificationToMerge;
	
	public RequestSpecification requestSpecification() throws IOException {
		/**if the RequestSpecification object is null, it indicates that 
		 * this is the first time execution came to this block and
		 * we have to create a file logging.txt
		 * if not then we need to append logs to it.
		 **/
		if(requestSpecificationToMerge == null) {
			// creates logging.txt 
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			requestSpecificationToMerge =  new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseUrl"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON)
				.build();
			return requestSpecificationToMerge;	
		}
		return requestSpecificationToMerge;	
	}
	
	Properties prop;
	FileInputStream fis;
	public String getGlobalValue(String key) throws IOException {
		if(prop == null && fis == null) {
			prop = new Properties();
			fis = new FileInputStream(".\\src\\test\\java\\resources\\global.properties");
			prop.load(fis);
			return prop.getProperty(key);		
		}
		return prop.getProperty(key);	
	}
	
	public String getJsonPath(Response response, String key) {
		String resp= response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
}
