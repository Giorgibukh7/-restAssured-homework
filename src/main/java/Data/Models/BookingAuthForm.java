package Data.Models;

import org.json.JSONObject;

public class BookingAuthForm {
    public JSONObject form = new JSONObject()
            .put("password","password123")
            .put("username","admin");
}
