package Steps;

import Data.Models.BookingAuthForm;
import Data.Models.BookingDates;
import Data.Models.BookingFormLombok;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingSteps {
    BookingAuthForm auth = new BookingAuthForm();
    String BaseURL = "https://restful-booker.herokuapp.com/booking/";
    String AuthURL = "https://restful-booker.herokuapp.com/auth";

   public String GetToken(){
        return RestAssured.given().baseUri(AuthURL).contentType(ContentType.JSON)
                        .body(auth.form.toString())
                        .when()
                        .post()
                        .then().extract().jsonPath().get("token");
    }
    @Step("send put request to UpdateBooking")
    public Response putRequest(String body,String Id){
        return given()
                .baseUri(BaseURL + Id)
                .filter(new AllureRestAssured())
                .contentType("application/json")
                .header("Cookie","token="+ GetToken())
                .body(body)
                .when()
                .put();
    }
    @Step("Create Form {0}")
    public BookingFormLombok createFormFromArray(Object[] array){
        BookingFormLombok form = new BookingFormLombok();
        BookingDates dates = new BookingDates();
        dates.setCheckIn(array[4].toString());
        dates.setCheckOut(array[5].toString());

        form.setName(array[0].toString());
        form.setLastName(array[1].toString());
        form.setBookingDates(dates);
        form.setTotalPrice((Integer) array[2]);
        form.setDepositPaid((Boolean) array[3]);
        form.setAdditionalNeeds(array[6].toString());

        return form;
    }

}
