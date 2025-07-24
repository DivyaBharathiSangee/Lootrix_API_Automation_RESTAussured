package Lootrix_Load;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
public class Lootrix2 {

	  // Base logic for POST calls
    public Response sendPostRequest(String url, JSONObject payload) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(payload != null ? payload.toString() : "")
                .when()
                .post(url);
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
        payload.put("ProviderName", "jEQn2f0rPpIme8fPbZCJc1JQK9QzyZLKNrnA104rYpOCj7==");
        payload.put("environment", "jEQn2f0rPpImsNrbcVNCVsR4xphPnpLO3E4aQXyFXKKk2A188Qf88wHWnBj4vB6l6YVC5CQmW3X78KV9Sb3z360nmXeMtQTEQRRBTtYm52voSDoO5pVJLsa=");
        payload.put("gameName", gameName);
        payload.put("operator", "jEQn2f0rPpImgN1CoBNCVsR4xphPnpMBTY5tRX1qWqOT3X5EVz16Ww0AosbyjfL75rJ9TpQiWQ576078TMOn3JV9nayOi6hc2YOp5KDC4v0G4doiVZWyLsa=");
        payload.put("token", "jEQn2f0rPpIm3qXq0GJb1MiCl9mSxqTl4IqGPDueW3OT2d6nWwfaW9W8AS4MvSHBgYirTmJAXQT8iDCpUP0lT63EnaiNjqlFQANxOtkxg5WpUCNz528u1E0h5TR8s8RCTWy5RZIkRk8tTZZq3t4GcmZHcMzWmMh5l0POSYrq2Kdq9KPC2AXb76H7XQnTnBcNwsPC0s5=");
        return payload;
    }
   
    

    @DataProvider(name = "gameEndpoints")
    public Object[][] gameEndpoints() {
        return new Object[][]{
               // {"https://d30ogb4y69iifg.cloudfront.net/live", null, false},
                {"https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/hilo", getEncryptedPayload("jEQn2f0rPpImrNrBoBNCVsR4xphPnpLOSY4DPDWaWqimRUT7WzUq96m8o8OPwvT74oKpTJQk7t5CjK3ESPSmRt8rmaLyi6T8RbJ55K155MTr4dhB5ZJfLsa="), true},
                {"https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/roulette", getEncryptedPayload("jEQn2f0rPpImttfaoJ0aqMuTk5eVK3IBNoup1XCd83XF3DP6VTXBXM1RnBrvjv1z57ND6WJD7c1Dj0V8UMZ73J0nzUyNhdtFPBlaTN175Iu94mUhVZJdQYXx5ckI"), true},
                {"https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/mines", getEncryptedPayload("jEQn2f0rPpIms3rDc0RsYIjZKpaQhqrASoG82n0aXK4RQjP986f6WwXRoSF2jVL5hr4n5CkjVwYs7K8o4v78Sc8qnxdttTlGQVN7TKYq4IScUdgk6s8y1nTm"),true},
                {"https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/keno", getEncryptedPayload("jEQn2f0rPpImr91DoBNCVsR4xphPnpLRRIm72049Y3TCQQTaVQMr9wKDnsoQks56goOrTGEjVJ1D6DBETMqpRtb9zuLutQyr2YJ65NLD52LsSTFATpqvLsa="), true},
                {"https://d1ufxpbx6fddl9.cloudfront.net/matchmake/joinOrCreate/tower", getEncryptedPayload("jEQn2f0rPpImuNfcc0NsYIjZKpaQhqrARrK7QKcaYKPD3a1DV6Uo8MG8pvcMjcsog70nVGok7JrF7XSsSPZ54N8soxt0hqhDReJ8T91Ahv4a5GQlUFtK1nTm"), true},
                {"https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/gocolor", getEncryptedPayload("jEQn2f0rPpImg9fso5CFpoi3hPM0x3PATnO8P04dYKrDRQrE7w188MnVpsKOjVUk6YWpUmEi83LC7nWrU2NBSQSpyUyMhtxF1bJ94qnA5IrrTDMP58VKQo4dOXB="), true},
                {"https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/windrop", getEncryptedPayload("jEQn2f0rPpImu9rDcKOFpIi3hPM0x3PATnO8RXvvYN5GQDT98QYoX6SCAfn1vS17574sTGkjVJYpi0ba5s4k4wR7yDmNitx9PRR94qgq654E5DIkUFOx1YLvOXB="), true},
                {"https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/wheels", getEncryptedPayload("jEQn2f0rPpImu9nucZCJVo6TJ9wOxFLoNoGb169tWqSoRXXC79ImX6WAnvf4kvL441Sn6WJA8N597DCoTb3zTQbFnxmKuaytPeZa4314gbTq5d2jUFVh2Iqvlq=="),true},
                {"https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/sevenupsevendown", getEncryptedPayload("jEQn2f0rPpIm4a1F05WFq8ETk5eVK3IBNoup2q0dXtWSRjP7Vf2nWMOBnBnzkv6lhYlFVWolWw6qiqb8S2p5S3xCmeTZiTut1kWnT0T94588TdNz6pKuPrTx5ckI"), true},
                {"https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/hotline", getEncryptedPayload("jEQn2f0rPpImrNf9oJqEcYi3hPM0x3PATnOEQK0a9KTF2A6oVTQmW6PVpfj3wcXA5ENCUpNDW3koj037SIuoSQ3bmeUOjqqp1bNaS3P6gvS9Tdok6M8x1YWLOXB="),true},
                {"https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/baccarat", getEncryptedPayload("jEQn2f0rPpImftLsb5KIb3qTk5eVK3IBNoupPKXq8tKlQdP6VTUpW6O7zS8QwfQp61JE5pVBX3XDj0ZDTMWo3J8qzettuQMsQVh949T84PWGTdZDVJtJQrSLT8kI"),true},
                {"https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/balloon", getEncryptedPayload("jEQn2f0rPpImftLBoJeFooi3hPM0x3PATnPsPnXqX0PD2QL976P89zS8AS3vj8LC5Eqn6WIO7JsrjXWrT5V84NZFohpZhaPa1Rqn4t1Cg50a4TUl52RKPEmJOXB="),true},
                {"https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/limbo", getEncryptedPayload("jEQn2f0rPpImsNrCbpdsYIjZKpaQhqrARrWaPH5sWGTERUT7XQLE9wmFn8b2vSko5oipVWQjWwX8708tTbSpR3conauLjaas1bFDS0D94I475dhCVJRg1XTm"),true},
                {"https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/", getEncryptedPayload(""),true},
               // {"https://d2ytj2b15i3h1o.cloudfront.net/matchmake/joinOrCreate/", getEncryptedPayload(""),true}
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
