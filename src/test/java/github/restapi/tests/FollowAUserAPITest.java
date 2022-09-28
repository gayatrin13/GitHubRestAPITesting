package github.restapi.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import github.restapi.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FollowAUserAPITest extends TestBase {
	Response response;

	@BeforeClass
	public void followSpecificUserTest() { // follow a specific user
		System.out.println("*************************FollowAUserAPITest**************************************************");
		response = RestAssured.given()/* .header("Content Length", "0") */.auth().oauth2(oauthToken)
				.put("/user/following/npatilcsod");
		System.out.println("status code :" + response.getStatusCode());
	//	System.out.println("followSpecificUserTest :" + response.asPrettyString());

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
