package github.restapi.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import github.restapi.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetUserFollowers extends TestBase {
	Response response;

	@BeforeClass
	public void getAllFollwers() {

		response = RestAssured.given().auth().oauth2(oauthToken).get("/users/gayatrin13/followers");
	}

	@Test
	public void testStatusCode() { // lists all public apis
		Assert.assertEquals(response.getStatusCode(), RESPONSE_CODE_200);
	}

	@Test
	public void testStatusLine() {
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
	}

	@Test
	public void testHeaderServer() {
		Assert.assertEquals(response.getHeader("Server"), HEADER_Server);
	}

	@AfterClass
	public void cleanUp() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
