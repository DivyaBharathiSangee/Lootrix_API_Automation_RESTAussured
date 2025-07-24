package Lootrix_Load;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;


public class LootrixGames {

	 //@Test
	    public void testStatusCodeAviator() {
	    	String jsonBody = "jEQB3fOb6374eaXepJZs1ojiyqmrJ4UK4ZXogKWrVJPH2d8r6f8ml90SpRcMKVskvLeEPq4=";
	        Response response = RestAssured
	            .given()
	            .body(jsonBody)
	            .when()
	            .post( "https://d30ogb4y69iifg.cloudfront.net/live");

	        // Print response (optional)
	        response.then().log().all();

	        // Assert status code
	        try {
	            Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");
	            System.out.println("✅ Assertion passed.");
	        } 
	        catch (AssertionError e) {
	            System.out.println("❌ Assertion failed: Status code is not 200");
	            throw e; // Re-throw to let TestNG report the failure
	        }
	        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");
	    }
	// @Test
	    public void testStatusCodeCricketX() {
	        Response response = RestAssured
	            .given()
	            .when()
	            .post("https://d30ogb4y69iifg.cloudfront.net/live");

	        // Print response (optional)
	        response.then().log().all();

	        // Assert status code
	        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");
	    }
	 
	
	// @Test
	    public void testStatusCodeHilo() {
	        Response response = RestAssured
	            .given()
	            .when()
	            .post("https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/hilo");

	        // Print response (optional)
	        response.then().log().all();

	        // Assert status code
	       Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");
	        
	    }
	// @Test
	    public void testStatusCodeRoulette() {
	        Response response = RestAssured
	            .given()
	            .when()
	            .post("https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/roulette");

	        // Print response (optional)
	        response.then().log().all();

	        // Assert status code
	       Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");
	        
	    }
	 
	// @Test
	    public void testStatusCodeKeno() {
	        Response response = RestAssured
	            .given()
	            .when()
	            .post("https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/keno");

	        // Print response (optional)
	        response.then().log().all();

	        // Assert status code
	       Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");
	        
	    }
	 
	@Test
	    public void testStatusCode7Up7Down() {
			JSONObject payload=new JSONObject();
		//	payload.put("ProviderName","jEQn2f0rPpIme8fPbZCJc1JQK9QzyZLKNrnA104rYpOCj7==");
			//payload.put("", false)
			payload.put("ProviderName", "jEQn2f0rPpIme8fPbZCJc1JQK9QzyZLKNrnA104rYpOCj7==");
	        payload.put("environment", "jEQn2f0rPpImsNrbcVNCVsR4xphPnpLO3E4aQXyFXKKk2A188Qf88wHWnBj4vB6l6YVC5CQmW3X78KV9Sb3z360nmXeMtQTEQRRBTtYm52voSDoO5pVJLsa=");
	        payload.put("gameName", "jEQn2f0rPpIm4a1F05WFq8ETk5eVK3IBNoup2q0dXtWSRjP7Vf2nWMOBnBnzkv6lhYlFVWolWw6qiqb8S2p5S3xCmeTZiTut1kWnT0T94588TdNz6pKuPrTx5ckI");
	        payload.put("operator", "jEQn2f0rPpImgN1CoBNCVsR4xphPnpMBTY5tRX1qWqOT3X5EVz16Ww0AosbyjfL75rJ9TpQiWQ576078TMOn3JV9nayOi6hc2YOp5KDC4v0G4doiVZWyLsa=");
	        payload.put("token", "jEQn2f0rPpIm3qXq0GJb1MiCl9mSxqTl4IqGPDueW3OT2d6nWwfaW9W8AS4MvSHBgYirTmJAXQT8iDCpUP0lT63EnaiNjqlFQANxOtkxg5WpUCNz528u1E0h5TR8s8RCTWy5RZIkRk8tTZZq3t4GcmZHcMzWmMh5l0POSYrq2Kdq9KPC2AXb76H7XQnTnBcNwsPC0s5=");
	        Response response = RestAssured
	            .given()
	            .contentType(ContentType.JSON)
                .body(payload.toString())
	            .when()
	            .post("https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/sevenupsevendown");

	        // Print response (optional)
	        response.then().log().all();
	        // Assert status code 
	        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");
	        response.then()
	        .body("room.name", equalTo("sevenupsevendown"))
	        .body("room.roomId", not(emptyOrNullString()))
	        .body("sessionId", not(emptyOrNullString()));
	    }
	//@Test
	    public void testStatusCodeWheels() {
	        Response response = RestAssured
	            .given()
	            .when()
	            .post("https://d1ufxpbx6fddl9.cloudfront.net/matchmake/joinOrCreate/wheels");

	        // Print response (optional)
	        response.then().log().all();

	        // Assert status code
	       Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");
	      
	       response.then().body("room.name", equalTo("wheels"));
	    }
	 
	// @Test
	    public void testStatusCodeGocolor() {
	        Response response = RestAssured
	            .given()
	            .when()
	            .post("https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/gocolor");

	        // Print response (optional)
	        response.then().log().all();

	        // Assert status code
	       Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");
	        
	    }
	 
	 
	 
}
