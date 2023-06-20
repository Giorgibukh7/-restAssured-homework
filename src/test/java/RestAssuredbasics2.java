import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class RestAssuredbasics2 {
    String jsonBody = "{\n" +
            "    \"firstname\": \"Bogus\",\n" +
            "    \"lastname\": \"Bingus\",\n" +
            "    \"totalprice\": 420,\n" +
            "    \"depositpaid\": false,\n" +
            "    \"bookingdates\": {\n" +
            "        \"checkin\": \"2023-06-23\",\n" +
            "        \"checkout\": \"2023-06-30\"\n" +
            "    },\n" +
            "    \"additionalneeds\": \"Money\"\n" +
            "}";
    @Test
    public void TestMethodOne()
    {
        ValidatableResponse response =
                RestAssured.given().baseUri("https://restful-booker.herokuapp.com/booking").contentType(ContentType.JSON)
                .body(jsonBody)
                .log().all()
                .when()
                .post()
                .then();
                        //.log().ifStatusCodeIsEqualTo(201);
        int statusCode = response.extract().statusCode();
        if(statusCode ==201){
            response.log().body();
        }

    }
    @Test
    public void TestMethodTwo()
    {

        ValidatableResponse response =
                RestAssured.given().baseUri("https://bookstore.toolsqa.com/BookStore/v1/Books")
                        .when()
                        .get()
                        .then();


        ArrayList<String> isbnNumbers = response.extract().jsonPath().get("books.isbn");
        ArrayList<String> dates = response.extract().jsonPath().get("books.publish_date");

        String LastISBNNumber =isbnNumbers.get(isbnNumbers.size()-1);
        Assert.assertTrue(LastISBNNumber.startsWith("9"));

        for (String date : dates) {

            String filteredDateString = date.replaceAll("T"," ").replaceAll(".000Z","");

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String currentTime = dtf.format(now);

            Assert.assertTrue(currentTime.compareTo(filteredDateString) > 0);

        }
    }
}