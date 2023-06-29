import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;



import static org.hamcrest.Matchers.*;

public class RestAssuredAuthTest {





    String DeleteBookId = "2";
    String BaseURL = "https://restful-booker.herokuapp.com/auth";
    JSONObject jsonBody = new JSONObject()
            .put("password","password123")
            .put("username","admin");
    @Test
    public void AuthDeleteTest()
    {
        String token =
                RestAssured.given().baseUri(BaseURL).contentType(ContentType.JSON)
                        .body(jsonBody.toString())
                        .when()
                        .post()
                        .then().extract().jsonPath().get("token");
        System.out.println(token);


                RestAssured.given().contentType(ContentType.JSON).header("Cookie","token="+token)
                        .when()
                        .delete("https://restful-booker.herokuapp.com/booking/"+DeleteBookId)
                        .then().log().all().and().assertThat().statusCode(201);



    }
    @Test
    public void HamcrestAssertionsTest(){
        RestAssured.given().contentType(ContentType.JSON)
                .when()
                .get("http://ergast.com/api/f1/2017/circuits.json")
                .then().assertThat().body("MRData.CircuitTable.Circuits.circuitId",hasItem("marina_bay")).and()
                .assertThat().body("MRData.CircuitTable.Circuits[-1].Location.long.toDouble()",anyOf(greaterThan(1d),equalTo(10d))).and()
                .assertThat().body("MRData.CircuitTable.Circuits[-1].Location.country",equalTo("UAE")).and()
                .assertThat().body("MRData.CircuitTable.Circuits[1].Location.country",equalTo("USA"));

    }
}
