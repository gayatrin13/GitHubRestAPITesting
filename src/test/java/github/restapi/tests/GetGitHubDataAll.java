package github.restapi.tests;

import org.testng.annotations.Test;

import github.restapi.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetGitHubDataAll extends TestBase {
	@Test
	public void getEmojisAPITest() {

		Response response = RestAssured.given().get("/emojis");
		// System.out.println("response :" + response.asPrettyString());
		System.out.println("status code :" + response.getStatusCode());
	}

	@Test
	public void getUsersAPITest() { // requires authentication
		Response response = RestAssured.given().auth().oauth2(oauthToken).get("/user");
		System.out.println("getUsersAPITest response :" + response.asPrettyString());
		System.out.println("status code :" + response.getStatusCode());
	}

	@Test
	public void getUserNotifications() {
		Response response = RestAssured.given().auth().oauth2(oauthToken).get("/notifications");
		System.out.println("response :" + response.asPrettyString());
		System.out.println("status code :" + response.getStatusCode());
	}

	@Test
	public void getFollowersOfUser() {
		Response response = RestAssured.given().auth().oauth2(oauthToken).get("/user/followers");
		System.out.println("status code :" + response.getStatusCode());
		System.out.println(" getFollowersOfUser response :" + response.asPrettyString());
	}

	@Test
	public void getFollowedByUser() {
		Response response = RestAssured.given().auth().oauth2(oauthToken).get("/user/following");
		System.out.println("status code :" + response.getStatusCode());
		System.out.println("getFollowedByUser response :" + response.asPrettyString());
	}
}
