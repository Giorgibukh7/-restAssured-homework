
import Data.SoapAPIData;
import Requests.SoapRequest;
import Steps.ContinentAPISteps;
import org.testng.Assert;
import org.testng.annotations.Test;
import Data.ContinentAPIData;

public class RestAssuredXML {

    ContinentAPISteps steps = new ContinentAPISteps();
    ContinentAPIData data = new ContinentAPIData();
    SoapRequest SOAPrequest = new SoapRequest();

    SoapAPIData data1 = new SoapAPIData();
    @Test
    void XMLPathTest() {

           steps.validateCountsName(data.ListSize)
                    .validatesNames(data.Continents)
                   .validateSNameWithSCode(data.AN)
                   .validateLastSName(data.LastSName);
    }
    @Test
    void soapTest(){
   String street = SOAPrequest.findPerson().then().extract().xmlPath().getString("Envelope.Body.FindPersonResponse.FindPersonResult.Home.Street");
   String zip = SOAPrequest.findPerson().then().extract().xmlPath().getString("Envelope.Body.FindPersonResponse.FindPersonResult.Office.Zip");

        Assert.assertEquals(street,data1.Street);
        Assert.assertEquals(zip,data1.zip);

    }
}
