import Models.UserData;
import io.restassured.RestAssured;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.testng.annotations.Test;

public class ReqTest {
    UserData user1=new UserData("Ahmed","leader");
    @Test
    public void testGet(){
        RestAssured.given().baseUri("https://reqres.in")
                .when().get("api/users/2").
                then().assertThat().statusCode(200).log().all();
    }
    @Test
    public void testGetList(){
        RestAssured.given().baseUri("https://reqres.in").basePath("api")
                //.port(443)
                .param("page","2")
                .when().get("/users")
                .then().assertThat().statusCode(200).log().all();
    }
    @Test
    public void testPost(){
        RestAssured.given().baseUri("https://reqres.in").basePath("api")
                .contentType("application/json")
                .body(user1)
                .when().post("/users")
                .then().assertThat().statusCode(201).log().all();
    }
    @Test
    public void testPut(){
        String body = "{\n" +
                "    \"name\": \"Ahmed\",\n" +
                "    \"job\": \"zion resident\",\n" +
                "    \"updatedAt\": \"2023-07-22T09:01:38.346Z\"\n" +
                "}";
        RestAssured.given().baseUri("https://reqres.in").basePath("api")
                .contentType("application/json")
                .body(body)
                .when().put("/users/2")
                .then().assertThat().statusCode(200).log().all();
    }
    @Test
    public void testDelete(){
        RestAssured.given().baseUri("https://reqres.in").basePath("api")
                .when().delete("/users/2")
                .then().assertThat().statusCode(204).log().all();
    }
}
