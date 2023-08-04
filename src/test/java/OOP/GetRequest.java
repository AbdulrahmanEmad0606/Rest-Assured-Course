package OOP;

import Models.GetComplexResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.lang.invoke.StringConcatFactory;

public class GetRequest {
    String baseUri;
    String endPoint;
    RequestSpecification request;
    public GetRequest(String baseUri,String endPoint){
        this.endPoint=endPoint;
        this.baseUri=baseUri;
        request= RestAssured.given().baseUri(baseUri).basePath(endPoint);
    }
    public void setHeaders(String header, String value){
        request.header(header,value);
    }
    public void setQueryParameters(String parameter,String value){
        request.param(parameter,value);
    }
    public Response send(){
        return request.get();
    }

}
