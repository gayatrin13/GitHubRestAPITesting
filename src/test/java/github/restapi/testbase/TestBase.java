package github.restapi.testbase;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.RestAssured;

public class TestBase {
	public Properties props;
	protected static String oauthToken;
	protected static int RESPONSE_CODE_200 = 200;
	protected static int RESPONSE_CODE_201 = 201;
	protected static int RESPONSE_CODE_204 = 204;

	protected static int RESPONSE_CODE_400 = 400;
	protected static int RESPONSE_CODE_401 = 401;
	protected static int RESPONSE_CODE_500 = 500;

	protected static String HEADER_CONTENT_TYPE = "application/json; charset=utf-8";
	protected static String HEADER_Server = "GitHub.com";
	protected static String HEADER_Content_Encoding = "gzip";
	protected static String STATUS_LINE = "HTTP/1.1 200 OK";

	// protected Logger logger = LogManager.getLogger(TestBase.class);

	public TestBase() {
		// System.out.println("logger level:" + logger.getLevel());
		try {
			props = new Properties();
			FileInputStream fis = new FileInputStream(
					new File(System.getProperty("user.dir") + "\\src\\test\\java\\github\\config\\config.properties"));
			props.load(fis);
			// logger.info(props.getProperty("base_uri"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		oauthToken = props.getProperty("github_token");
		RestAssured.baseURI = props.getProperty("base_uri");

	}
}
