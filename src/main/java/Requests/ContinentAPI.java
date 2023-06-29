package Requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class ContinentAPI {
    public Response CallAPI(){
        return given().when().get("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByName");
    }
}
