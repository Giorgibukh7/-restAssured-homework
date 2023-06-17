import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RestAssuredFirstTest {
    public static class provider {
        @DataProvider(name = "provider")
        public static Object[][] CircuitIdAndCountry() {
            return new Object[][]{

                    {"1","USA"},
                    {"5", "Hungary"},

            };
        }
    }
    @Test(dataProvider = "provider",dataProviderClass = provider.class)
    void testbody(String[] CircuitData){

        String circuitIndex = CircuitData[0];
        String circuitCountry = CircuitData[1];

        String  pathToCircuit = "MRData.CircuitTable.Circuits["+circuitIndex+"]";

        Response firstResponse = RestAssured.given()
                .when()
                .get("http://ergast.com/api/f1/2017/circuits.json")
                .then().extract().response();


        Assert.assertEquals(circuitCountry,firstResponse.jsonPath().get(pathToCircuit + ".Location.country").toString());

        String circuitId = firstResponse.jsonPath().get(pathToCircuit + ".circuitId").toString();
        String parametrisedURL = "http://ergast.com/api/f1/2017/circuits/"+circuitId+".json";

        Response secondResponse = RestAssured.given()
                .when()
                .get(parametrisedURL)
                .then().extract().response();

        Assert.assertEquals(circuitCountry,secondResponse.jsonPath().get("MRData.CircuitTable.Circuits[0].Location.country").toString());

    }
}
