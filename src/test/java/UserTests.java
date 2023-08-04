import Models.GetComplexResponse;
import Models.GetUserResponseData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserTests {
    @Test
    public void testDeserialization(){
        RequestSpecification requestSpecification= given().baseUri("https://reqres.in").basePath("api").param("id","2");
        Response response=requestSpecification.get("/users");
        response.prettyPrint();
        GetUserResponseData getUserResponseData=response.as(GetUserResponseData.class);
        Assert.assertEquals(getUserResponseData.data.id,2);
        Assert.assertEquals(getUserResponseData.data.first_name,"Janet");
    }
    @Test
    public void testGetData(){
        RestAssured.given().baseUri("https://reqres.in")
                .when().get("api/users").
                then().
                assertThat().body("data.id",is(hasItems(1,2,3,4,5)));// check for more than one value
                //assertThat().body("data.id",is(hasItem(1)));//retrieve all data and check is contained or not
                //assertThat().body("data.id",is(containsInAnyOrder(2,1,3,4,6,5)));//containsInAnyOrder=>same value, same count
                //assertThat().body("data.id",is(contains(1,2,3,4,5,6)));// contains=> same value, same count ,same order
                //assertThat().body("data.id",equalTo(2));
    }
    @Test
    public void testDeserializationComplexResponse(){
        RequestSpecification requestSpecification= given().baseUri("https://reqres.in").basePath("api");
        Response response=requestSpecification.get("/users");
        response.prettyPrint();
        GetComplexResponse getComplexResponse=response.as(GetComplexResponse.class);
        Assert.assertEquals(getComplexResponse.data.get(0).first_name,"George");
    }
}
