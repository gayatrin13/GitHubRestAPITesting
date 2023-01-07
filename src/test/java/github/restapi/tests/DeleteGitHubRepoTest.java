package github.restapi.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import github.restapi.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteGitHubRepoTest extends TestBase {
	Response response;

	@BeforeClass
	public void deleteRepository() { // RestAPIAutomationUsingHttpClients //SeleniumBasicsProject //Guru99DemoPOM
										// //TestNGProject
		System.out.println("******************************DeleteGitHubRepoTest*********************************************");

		String[] repo_names = { "RestAPIAutomationUsingHttpClients", "SeleniumBasicsProject", "Guru99DemoPOM",
				"TestNGProject" };
		for (int i = 0; i < repo_names.length; i++) {
			response = RestAssured.given().auth().oauth2(oauthToken).delete("/repos/gayatrin13/" + repo_names[i]);
			System.out.println("Status Code :" + response.getStatusCode());
		//	System.out.println("response :" + response.asPrettyString());
		}

	}

	@Test
	public void testStatusCode() { // lists all public apis
		Assert.assertEquals(response.getStatusCode(), RESPONSE_CODE_204);
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
