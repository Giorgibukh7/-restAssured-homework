package Utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseProcessing {
    
    public static void DeserializeByStatusCode(Response response){
        if(response.getStatusCode() == 200){
            RegistrationSuccessResponse responseBody = response.as(RegistrationSuccessResponse.class);

            Assert.assertEquals(4,responseBody.id);
            Assert.assertEquals("QpwL5tke4Pnpja7X4",responseBody.token);
        } else if (response.getStatusCode() == 400) {
            RegistrationFailureResponse responseBody = response.as(RegistrationFailureResponse.class);

            Assert.assertEquals("Missing password",responseBody.error);
        }
    }
}
