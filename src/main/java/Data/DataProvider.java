package Data;

public class DataProvider {
    public static class provider {
        @org.testng.annotations.DataProvider(name = "Bookings")
        public static Object[][] CircuitIdAndCountry() {
            return new Object[][]{

                    {"Bogus","Bingus",1000,true,"2023-06-13","2023-07-12","aloma mola"},
                    {"Name","LastName",11000,false,"2023-05-12","2023-11-11","asdasd"}

            };
        }
    }
}
