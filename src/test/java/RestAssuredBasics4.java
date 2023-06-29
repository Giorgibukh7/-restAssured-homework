import Data.DataProvider;
import Data.Models.BookingFormLombok;
import Data.Models.BookingFormResponse;
import Steps.BookingSteps;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class RestAssuredBasics4 {
    BookingSteps steps = new BookingSteps();
    BookingFormLombok form = new BookingFormLombok();
    ObjectMapper mapper;

    @Test(dataProvider = "Bookings",dataProviderClass = DataProvider.provider.class)
    void methodOne(Object[] Bookings) throws JsonProcessingException {
    mapper = new ObjectMapper();

        form = steps.createFormFromArray(Bookings);

        Response response = steps.putRequest(mapper.writeValueAsString(form),"3");

        Assert.assertEquals(response.statusCode(),200);
        BookingFormResponse deserializedForm = response.as(BookingFormResponse.class);

        Assert.assertEquals(deserializedForm.getName(),form.getName());



    }
}
