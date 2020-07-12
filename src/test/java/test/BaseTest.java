package test;

import api.auth.PostToken;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeSuite;
import utils.PropertiesManager;

public class BaseTest {

	public static final Logger logger = Logger.getLogger(BaseTest.class);
	protected static String access_Token,accessToken;
	String country_property = "uk";
	

	@BeforeSuite(alwaysRun=true)
	public void BeforeSuite() throws Exception
	{
        PropertyConfigurator.configure("src\\main\\resources\\log4j.properties");
		PropertiesManager.initializeProperties("uk");
	    logger.info("Properties Initialized");
		System.out.println(PropertiesManager.getProperty("baseURI").toString());
		PostToken postToken = new PostToken(PropertiesManager.getProperty("baseURI"));
		postToken.setContentType("application/x-www-form-urlencoded");
		postToken.addBodyParam("client_id",PropertiesManager.getProperty("client_id"));
		postToken.addBodyParam("client_secret",PropertiesManager.getProperty("client_secret"));
		postToken.addBodyParam("grant_type",PropertiesManager.getProperty("grant_type"));
		postToken.addBodyParam("username",PropertiesManager.getProperty("username"));
		postToken.addBodyParam("password",PropertiesManager.getProperty("password"));
		postToken.setExpectedStatusCode(200);
		postToken.perform();
		access_Token = postToken.getAccessToken();
		accessToken = "Bearer "+ access_Token;
	    logger.info("OAuth Token Received = " + accessToken);
	}

	// To do retrieve values from jenkins 
	public String load_countryProperty()
	{
		if (country_property == "")
		{
			return country_property="";
		}
		else if(country_property == ""){
			return country_property="";
		}
		else {
			return "";
		}
	}
}