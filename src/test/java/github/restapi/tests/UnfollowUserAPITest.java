package github.restapi.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import github.restapi.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UnfollowUserAPITest extends TestBase {
	Response response;

	@BeforeClass
	public void unfollowAUser() {
		System.out.println(
				"****************************UnfollowUserAPITest***********************************************");
		response = RestAssured.given().auth().oauth2(oauthToken).delete(props.getProperty("unfollow_user_uri"));
		// System.out.println("response :" + response.asPrettyString());
		System.out.println("status code :" + response.getStatusCode());
	}

	@Test
	public void testStatusCode() { // lists all public apis
		Assert.assertEquals(response.getStatusCode(), RESPONSE_CODE_204);
	}

	@Test
	public void testStatusLine() {
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 204 No Content");
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
