package Steps;


import Requests.ContinentAPI;
import io.restassured.path.xml.element.NodeChildren;
import org.testng.Assert;

import java.util.ArrayList;

public class ContinentAPISteps {
    ContinentAPI API = new ContinentAPI();
    public ContinentAPISteps validateCountsName(int size){
        NodeChildren sNames = API.CallAPI().then().extract().path("ArrayOftContinent.tContinent.sName");
        Assert.assertEquals(sNames.size(),size);
        return this;
    }
    public ContinentAPISteps validatesNames(ArrayList<String> names){
        NodeChildren sNames = API.CallAPI().then().extract().path("ArrayOftContinent.tContinent.sName");
        for(int i = 0; i < sNames.size(); i++){
            Assert.assertEquals(sNames.get(i).toString(),names.get(i));
        }
        return this;
    }
    public ContinentAPISteps validateSNameWithSCode(String sName){
        String name = API.CallAPI().then().extract().path("ArrayOftContinent.tContinent.findAll {it.sCode == 'AN'}.sName");
        Assert.assertEquals(name,sName);
        return this;
    }
    public ContinentAPISteps validateLastSName(String name){
        String LastSName = API.CallAPI().then().extract().path("ArrayOftContinent.tContinent[-1].sName");
        Assert.assertEquals(LastSName,name);
        return this;
    }
}
