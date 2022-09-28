package github.restapi.tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import github.restapi.testbase.TestBase;
import github.utils.ExcelDataReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateRepoAPITest extends TestBase {
	String filePath;
	String sheetname;
	Response response;

	@BeforeClass
	public void ssetUP() {
		filePath = props.getProperty("github_data_filepath");
		sheetname = props.getProperty("sheet_name");
	}

	@Test(dataProvider = "GitHubRepoData", priority = 0)
	public void createRepoTest(String repoName, String description, String homepage, String privateVal) {
		System.out.println("*********************CreateRepoAPITest*****************");
		JSONObject object = new JSONObject();
		object.put("name", repoName);
		object.put("description", description);
		object.put("homepage", homepage);
		object.put("private", privateVal);
		response = RestAssured.given().body(object).auth().oauth2(oauthToken).post("/user/repos");
		// System.out.println("createRepoTest response :" + response.asString());
		System.out.println("status code :" + response.getStatusCode());

	}

	@DataProvider(name = "GitHubRepoData")
	public String[][] getExcelData() {

		String[][] data = ExcelDataReader.getCellData(filePath, sheetname);
		return data;
	}

	@Test
	public void testStatusCode() { // lists all public apis
		Assert.assertEquals(response.getStatusCode(), RESPONSE_CODE_201);
	}

	@Test
	public void testHeaderContentType() {
		Assert.assertEquals(response.getHeader("Content-Type"), HEADER_CONTENT_TYPE);
	}

	@Test
	public void testStatusLine() {
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 " + RESPONSE_CODE_201 + " Created");
	}

	@Test
	public void testHeaderServer() {
		Assert.assertEquals(response.getHeader("Server"), HEADER_Server);
	}

	/*
	 * @Test public void testHeaderEncoding() {
	 * Assert.assertEquals(response.getHeader("Content-Encoding"),
	 * HEADER_Content_Encoding); }
	 */
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
