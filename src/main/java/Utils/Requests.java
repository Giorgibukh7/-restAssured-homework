package Utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Requests {
    public static Response SendPostJSONRequest(String URL,Object o){
        return RestAssured.given().baseUri(URL).contentType(ContentType.JSON)
                .body(o)
                .when()
                .post();
    }
}
