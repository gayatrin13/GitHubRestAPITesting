package github.restapi.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import github.restapi.testbase.TestBase;
import github.utils.ExcelDataReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteGitHubRepoTest extends TestBase {
	Response response;

	@BeforeClass
	public void deleteRepository() { // RestAPIAutomationUsingHttpClients //SeleniumBasicsProject //Guru99DemoPOM
										// //TestNGProject
		String filePath = props.getProperty("github_data_filepath");
		String sheetname = props.getProperty("sheet_name");
		String[][] data = ExcelDataReader.getCellData(filePath, sheetname);

		System.out.println(
				"******************************DeleteGitHubRepoTest*********************************************");

//		String[] repo_names = { "GitHubSampleRepo1", "GitHubSampleRepo2", "GitHubSampleRepo3", "GitHubSampleRepo4",
//				"GitHubSampleRepo5" };
//		for (int i = 0; i < repo_names.length; i++) {
		for (int i = 0; i < data.length; i++) {
			System.out.println("RepoName: " + data[i][0]);
			response = RestAssured.given().auth().oauth2(oauthToken)
					.delete(props.getProperty("delete_repo_uri") + data[i][0]);
			System.out.println("Status Code :" + response.getStatusCode());
			// System.out.println("response :" + response.asPrettyString());
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
