package github.restapi.tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import github.restapi.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UpdateRepoAPITest extends TestBase {
	Response response;

	@BeforeClass
	public void upadateRepoTest() {
		System.out.println(
				"****************************UpdateRepoAPITest***********************************************");
		JSONObject object = new JSONObject();
		object.put("description", "First GitHub Repo using Rest Assured/RestAPI");
		response = RestAssured.given().body(object).auth().oauth2(oauthToken).patch("/repos/gayatrin13/Hello-World");
		System.out.println("status code: " + response.getStatusCode());
		// System.out.println("response :" + response.asPrettyString());

	}

	@Test
	public void testStatusCode() { // lists all public apis
		Assert.assertEquals(response.getStatusCode(), RESPONSE_CODE_200);
	}

	@Test
	public void testHeaderContentType() {
		Assert.assertEquals(response.getHeader("Content-Type"), HEADER_CONTENT_TYPE);
	}

	@Test
	public void testStatusLine() {
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
	}

	@Test
	public void testHeaderServer() {
		Assert.assertEquals(response.getHeader("Server"), HEADER_Server);
	}

	@Test
	public void testHeaderEncoding() {
		Assert.assertEquals(response.getHeader("Content-Encoding"), HEADER_Content_Encoding);
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
