package github.restapi.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import github.restapi.testbase.TestBase;
import github.utils.ExtentReportListener;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@Listeners(value = ExtentReportListener.class)
public class GetSpecificUserRepoTest extends TestBase {
	Response response;

	@BeforeClass
	public void getSpecificUserRepoAPI() {
		System.out.println(
				"****************************GetSpecificUserRepoTest***********************************************");
		response = RestAssured.given().get("users/gayatrin13/repos");
		System.out.println("Status code: " + response.getStatusCode());
		// System.out.println("getSpecificUserRepoAPITest response :" +
		// response.asPrettyString());
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
