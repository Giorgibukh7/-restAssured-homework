package Data.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@JsonPropertyOrder({"lastname","firstname","totalprice","depositpaid","bookingdates","additionalneeds"})
@JsonIgnoreProperties("SalesPrice")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingFormResponse {
    @JsonProperty("firstname")
    private String Name;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("totalprice")
    private int totalPrice;
    @JsonProperty("depositpaid")
    private boolean depositPaid;
    @JsonProperty("bookingdates")
    private BookingDates bookingDates;
    @JsonProperty("additionalneeds")
    private String additionalNeeds;

    private String SalesPrice;
}
