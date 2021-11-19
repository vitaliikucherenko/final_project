package com.nixsolutions.apiTests.createNewUsers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

public class CreateNewUsersWithoutTasks {

    String registerUrl = "http://users.bugred.ru/tasks/rest/doregister";
    String loginUrl = "http://users.bugred.ru/tasks/rest/dologin";
    JSONObject requestBody = new JSONObject();
    SoftAssert assertTest = new SoftAssert();

    public void registerNewUser(String email, String name, String password) {
        requestBody.put("email", email);
        requestBody.put("name", name);
        requestBody.put("password", password);
        String json = given()
                .contentType("application/json")
                .body(requestBody)
                .when().post(registerUrl).then().log().body()
                .statusCode(200).extract().asString();
        Assert.assertEquals(from(json).get("name"), requestBody.get("name"));
    }

    public void UserNameExist(String email, String name, String password) {
        requestBody.put("email", email);
        requestBody.put("name", name);
        requestBody.put("password", password);
        String json = given()
                .contentType("application/json")
                .body(requestBody)
                .when().post(registerUrl).then().log().body()
                .statusCode(200).extract().asString();
        assertTest.assertEquals(from(json).get("type"), "error");
        assertTest.assertEquals(from(json).get("message"), " Текущее ФИО " + requestBody.get("name") + " уже есть в базе");
        assertTest.assertAll();
    }

    public void UserEmailExist(String email, String name, String password) {
        requestBody.put("email", email);
        requestBody.put("name", name);
        requestBody.put("password", password);
        String json = given()
                .contentType("application/json")
                .body(requestBody)
                .when().post(registerUrl).then().log().body()
                .statusCode(200).extract().asString();
        assertTest.assertEquals(from(json).get("type"), "error");
        assertTest.assertEquals(from(json).get("message"), " email " + requestBody.get("email") + " уже есть в базе");
        assertTest.assertAll();
    }

    public void UserLogIn(String email, String password) {
        requestBody.put("email", email);
        requestBody.put("password", password);
        Response response =
                RestAssured.given()
                        .contentType("application/json")
                        .body(requestBody)
                        .when().post(loginUrl).then().log().body()
                        .statusCode(200).extract().response();
        Assert.assertEquals(response.path("result").toString(), "true");
    }

    public void UserIncorrectLogIn(String email, String password) {
        requestBody.put("email", email);
        requestBody.put("password", password);
        Response response =
                RestAssured.given()
                        .contentType("application/json")
                        .body(requestBody)
                        .when().post(loginUrl).then().log().body()
                        .statusCode(200).extract().response();
        Assert.assertEquals(response.path("result").toString(), "false");
    }
}
