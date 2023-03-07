package base;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import utils.Utility;

public class BaseTest 
{
	@BeforeClass
	public static void init()
	{
		RestAssured.baseURI = Utility.getConfigValue("BaseURI");
	}
}
