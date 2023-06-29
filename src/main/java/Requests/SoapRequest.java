package Requests;

import io.restassured.response.Response;

import java.io.File;
import static io.restassured.RestAssured.given;


public class SoapRequest {
    File file = new File("src/main/resources/file.xml");

    public Response findPerson(){
        return given()
                .header("SOAPAction","http://tempuri.org/SOAP.Demo.FindPerson")
                .header("ContentType","text/xml; charset=UTF-8")
                .body(file)
                .post("https://www.crcind.com:443/csp/samples/SOAP.Demo.cls");
    }
}
