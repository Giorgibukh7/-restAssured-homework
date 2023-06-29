import Utils.Requests;
import Data.Models.RegistrationForm;
import Steps.ResponseProcessing;
import Data.Models.UserForm;
import org.testng.annotations.Test;

public class DeserializationTest {

    RegistrationForm form = new RegistrationForm();
    UserForm user = new UserForm();
    String URL = "https://reqres.in/api/register";

    @Test
    void sendSuccessfulRequest(){

        form.setEmail("eve.holt@reqres.in");
        form.setPassword("pistol");

        ResponseProcessing.DeserializeByStatusCode(Requests.SendPostJSONRequest(URL,form));
    }
    @Test
    void sendFailedRequest(){

        form.setEmail("sydney@fife");

        ResponseProcessing.DeserializeByStatusCode(Requests.SendPostJSONRequest(URL,form));
    }
    @Test
    void callUsers(){
        String URL = "https://reqres.in/api/users";
        user.setName("morpheus");
        user.setJob("leader");
        Requests.SendPostJSONRequest(URL,user).then().assertThat().statusCode(201);
    }
}
