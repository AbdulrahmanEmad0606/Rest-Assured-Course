import OOP.GetRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UserTestsOOP {
    @Test
    public void testGetUsers(){
        GetRequest getUsers=new GetRequest("https://reqres.in","api/users/2");
        Response response = getUsers.send();
        response.prettyPrint();
    }
}
