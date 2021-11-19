package com.nixsolutions.apiTests.createNewUsers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import org.testng.Assert;

import java.util.Arrays;
import java.util.LinkedHashMap;

public class CreateNewUserWithTask {

    JSONObject requestBody = new JSONObject();
    String userWithTaskUrl = "http://users.bugred.ru/tasks/rest/createuserwithtasks";

    public void createUserWithTask(String email, String name, String title1, String desc1, String title2, String desc2,
                                   String comp1, String comp2, String hobby, String address, String name1, String surname1,
                                   String fathername1, String cat, String dog, String parrot, String cavy,
                                   String hamster, String squirrel, String phone, String inn, String gender,
                                   String birthday, String dateStart) {
        requestBody.put("email", email);
        requestBody.put("name", name);
        requestBody.put("tasks", Arrays.asList(new LinkedHashMap<String, Object>() {
                                                   {
                                                       put("title", title1);
                                                       put("description", desc1);
                                                   }
                                               },
                new LinkedHashMap<String, Object>() {
                    {
                        put("title", title2);
                        put("description", desc2);
                    }
                })
        );
        requestBody.put("companies", Arrays.asList(comp1, comp2));
        requestBody.put("hobby", hobby);
        requestBody.put("adres", address);
        requestBody.put("name1", name1);
        requestBody.put("surname1", surname1);
        requestBody.put("fathername1", fathername1);
        requestBody.put("cat", cat);
        requestBody.put("dog", dog);
        requestBody.put("parrot", parrot);
        requestBody.put("cavy", cavy);
        requestBody.put("hamster", hamster);
        requestBody.put("squirrel", squirrel);
        requestBody.put("phone", phone);
        requestBody.put("inn", inn);
        requestBody.put("gender", gender);
        requestBody.put("birthday", birthday);
        requestBody.put("date_start", dateStart);
        Response response =
                RestAssured.given()
                        .contentType("application/json")
                        .body(requestBody.toString())
                        .when().post(userWithTaskUrl).then()
                        .statusCode(200).extract().response();
        Assert.assertEquals(response.path("name").toString(), requestBody.get("name"));
    }
}
