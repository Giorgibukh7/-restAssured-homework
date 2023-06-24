import Utils.Requests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeserializationTest {

    RegistrationForm form = new RegistrationForm();
    UserForm user = new UserForm();
    String URL = "https://reqres.in/api/register";

    @Test
    void sendSuccessfulRequest(){

        form.setEmail("eve.holt@reqres.in");
        form.setPassword("pistol");

        ResponseProcessing.DeserializeByStatusCode(Requests.SendPostRequest(URL,form));
    }
    @Test
    void sendFailedRequest(){

        form.setEmail("sydney@fife");

        ResponseProcessing.DeserializeByStatusCode(Requests.SendPostRequest(URL,form));
    }
    @Test
    void callUsers(){
        String URL = "https://reqres.in/api/users";
        user.setName("morpheus");
        user.setJob("leader");
        Requests.SendPostRequest(URL,user).then().assertThat().statusCode(201);
    }
}
