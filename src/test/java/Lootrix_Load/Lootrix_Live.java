package Lootrix_Load;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
public class Lootrix_Live {


	  // Base logic for POST calls
  public Response sendPostRequest(String url, JSONObject payload) {
	  int retries = 3;
	    Response response = null;

	    while (retries-- > 0) {
      response= RestAssured
              .given()
              .header("User-Agent", "Mozilla/5.0")
              .header("Accept", "*/*")
              .contentType(ContentType.JSON)
              .body(payload != null ? payload.toString() : "")
              .when()
              .post(url);
      
      if (response.getStatusCode() == 200) 
    	  break;
	    }
	    return response;
  }

  // Basic status + optional body validation
  public void validateResponse(Response response, String expectedRoomName, boolean validateRoomInfo) {
      response.then().log().all(); // optional

      Assert.assertEquals(response.getStatusCode(), 200, "Expected status code is 200");

      if (validateRoomInfo) {
          response.then()
                  .body("room.name", equalTo(expectedRoomName))
                  .body("room.roomId", not(emptyOrNullString()))
                  .body("sessionId", not(emptyOrNullString()));
      }
  }

  // Payload for 7up7down (example only)
  public JSONObject getEncryptedPayload(String gameName) {
      JSONObject payload = new JSONObject();
      payload.put("ProviderName", "jEQn2f0rPpIme8fPbZCJc1JQLae2yOYBiLrCem0BStnN2dToXvLnp3==");
      payload.put("environment", "jEQn2f0rPpImtOPEcK0tqMV6KLdZhtkL31SpRm1vY0mm3dQsW6LDX6qBosFyvvQl5IqsTmhA7658iD3DT2N4S3N8oxx2htxEQVNB5qnC5Iu74DoOT8NdRY0eTdzEbSx=");
      payload.put("gameName", gameName);
      payload.put("operator", "jEQn2f0rPpImgN1CoBNCVsR4xphPnpMBTY5tRX1qWqOT3X5EVz16Ww0AosbyjfL75rJ9TpQiWQ576078TMOn3JV9nayOi6hc2YOp5KDC4v0G4doiVZWyLsa=");
      payload.put("token", "jEQn2f0rPpIm3G1v0pRH1ZM8mm9ylKkzS7ObRHcdYKOSRAoqVQf9XwPTnvoNlfP35o38TJREVcLb6HSq4IxBRNtFyaeKiTtF2UNxOtkxg5WpUCMPTp7KQ74JTtz6goN8TW894ZHzSb4u6Za94NTvcWSwZsrSym5znKIB4YSFQq0c83KpQDT97MYrWM4EAsbykVUm0s5=");
      payload.put("userId", "jEQn2f0rPpIm4GGd0GNfbFm4n6IQlZ3iSYK8O01uXGGN3AX9WwD996HWo8bxisP56XNyPplL76To8mZplZ==");
      return payload;
  }
 
  

  @DataProvider(name = "gameEndpoints")
  public Object[][] gameEndpoints() {
      return new Object[][]{
             // {"https://d30ogb4y69iifg.cloudfront.net/live", null, false},
              {"https://d6dacgab3gcoz.cloudfront.net/matchmake/joinOrCreate/hilo", getEncryptedPayload("jEQn2f0rPpImrNrBoBNCVsR4xphPnpLOSY4DPDWaWqimRUT7WzUq96m8o8OPwvT74oKpTJQk7t5CjK3ESPSmRt8rmaLyi6T8RbJ55K155MTr4dhB5ZJfLsa="), true},
            //  {"https://d6dacgab3gcoz.cloudfront.net/matchmake/joinOrCreate/roulette", getEncryptedPayload("jEQn2f0rPpImttfaoJ0aqMuTk5eVK3IBNoup1XCd83XF3DP6VTXBXM1RnBrvjv1z57ND6WJD7c1Dj0V8UMZ73J0nzUyNhdtFPBlaTN175Iu94mUhVZJdQYXx5ckI"), true},
             // {"https://d6dacgab3gcoz.cloudfront.net/matchmake/joinOrCreate/keno", getEncryptedPayload("jEQn2f0rPpImr91DoBNCVsR4xphPnpLRRIm72049Y3TCQQTaVQMr9wKDnsoQks56goOrTGEjVJ1D6DBETMqpRtb9zuLutQyr2YJ65NLD52LsSTFATpqvLsa="), true},
             // {"https://d1ufxpbx6fddl9.cloudfront.net/matchmake/joinOrCreate/wheels", null, true},
              //{"https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/gocolor", getEncryptedPayload("jEQn2f0rPpImg9fso5CFpoi3hPM0x3PATnO8P04dYKrDRQrE7w188MnVpsKOjVUk6YWpUmEi83LC7nWrU2NBSQSpyUyMhtxF1bJ94qnA5IrrTDMP58VKQo4dOXB="), true},
              //{"https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/sevenupsevendown", getEncryptedPayload("jEQn2f0rPpIm4a1F05WFq8ETk5eVK3IBNoup2q0dXtWSRjP7Vf2nWMOBnBnzkv6lhYlFVWolWw6qiqb8S2p5S3xCmeTZiTut1kWnT0T94588TdNz6pKuPrTx5ckI"), true}
      };
  }

  @Test(dataProvider = "gameEndpoints")
  public void testGameEndpoints(String url, JSONObject payload, boolean validateRoom) {
      String expectedRoomName = extractGameNameFromUrl(url); // e.g. "hilo"
      Response response = sendPostRequest(url, payload);
      validateResponse(response, expectedRoomName, validateRoom);
  }

  // Helper to extract game name from URL
  private String extractGameNameFromUrl(String url) {
      if (url.contains("/joinOrCreate/")) {
          return url.substring(url.lastIndexOf("/") + 1);
      }
      return "unknown";
  }
}
