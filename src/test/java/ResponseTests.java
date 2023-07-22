import Models.GetComplexResponse;
import Models.GetUserResponseData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ResponseTests {
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
                .when().get("api/users/2").
                then().assertThat().body("data.id",equalTo(2));
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
