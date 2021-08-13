package GetNet;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.lang.String;

public class ReqRes {

    @Test
    public void listUsers() {
        Response resp = RestAssured.get("https://reqres.in/api/users?page=2");
        int code = resp.getStatusCode();
        ResponseBody body = resp.getBody();
        String bodyAsString = body.asString();
        Assert.assertEquals(code, 200);
        System.out.println("Status code é: " + code);
        System.out.println("Response Body é: " + bodyAsString);
    }

    @Test
    public void singleUser() {
        String id = "2";
        Response resp = RestAssured.get("https://reqres.in/api/users/"+id);
        int code = resp.getStatusCode();
        ResponseBody body = resp.getBody();
        String bodyAsString = body.asString();
        Assert.assertEquals(code, 200);
        Assert.assertEquals(bodyAsString.contains(id), true);
        System.out.println("Status code é: " + code);
        System.out.println("Response Body é: " + bodyAsString);
    }

    @Test
    public void singleUsersNotFound() {
        String id = "23";
        Response resp = RestAssured.get("https://reqres.in/api/users/"+id);
        int code = resp.getStatusCode();
        Assert.assertEquals(code, 404);
        System.out.println("Status code é: " + code);
    }

    @Test
    public void listResource() {
        Response resp = RestAssured.get("https://reqres.in/api/unknown");
        int code = resp.getStatusCode();
        ResponseBody body = resp.getBody();
        String bodyAsString = body.asString();
        Assert.assertEquals(code, 200);
        System.out.println("Status code é: " + code);
        System.out.println("Response Body é: " + bodyAsString);
    }

    @Test
    public void singleResource() {
        String id = "2";
        Response resp = RestAssured.get("https://reqres.in/api/unknown/"+id);
        int code = resp.getStatusCode();
        ResponseBody body = resp.getBody();
        String bodyAsString = body.asString();
        Assert.assertEquals(code, 200);
        Assert.assertEquals(bodyAsString.contains(id), true);
        System.out.println("Status code é: " + code);
        System.out.println("Response Body é: " + bodyAsString);
    }

    @Test
    public void singleResourceNotFound() {
        String id = "23";
        Response resp = RestAssured.get("https://reqres.in/api/unknown/"+id);
        int code = resp.getStatusCode();
        Assert.assertEquals(code, 404);
        System.out.println("Status code é: " + code);
    }

    @Test
    public void createUser() {
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Jander Cerqueira");
        requestParams.put("job", "Analista QA");
        request.body(requestParams.toJSONString());
        Response resp = request.post("https://reqres.in/api/users");
        int code = resp.getStatusCode();
        ResponseBody body = resp.getBody();
        String bodyAsString = body.asString();
        Assert.assertEquals(code, 201);
        System.out.println("Status code é: " + code);
        System.out.println("Response Body é: " + bodyAsString);
    }

    @Test
    public void updateUser() {
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Update Jander Cerqueira");
        requestParams.put("job", "Analista QA 2");
        request.body(requestParams.toJSONString());
        Response resp = request.put("https://reqres.in/api/users/2");
        int code = resp.getStatusCode();
        ResponseBody body = resp.getBody();
        String bodyAsString = body.asString();
        Assert.assertEquals(code, 200);
        System.out.println("Status code é: " + code);
        System.out.println("Response Body é: " + bodyAsString);
    }

}