package com.nixsolutions.apiTests.createCompany;

import net.minidev.json.JSONObject;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

public class CreateNewCompany {

    SoftAssert assertTest = new SoftAssert();
    JSONObject requestBody = new JSONObject();
    String companyUrl = "http://users.bugred.ru/tasks/rest/createcompany";

    public void createNewOrganization(String companyName, String companyType, String companyUser1,
                                      String companyUser2, String emailOwner) {
        requestBody.put("company_name", companyName);
        requestBody.put("company_type", companyType);
        requestBody.put("company_users", Arrays.asList(companyUser1, companyUser2));
        requestBody.put("email_owner", emailOwner);
        String json = given()
                .contentType("application/json")
                .body(requestBody)
                .when().post(companyUrl).then().log().body()
                .statusCode(200).extract().asString();
        assertTest.assertEquals(from(json).get("type"), "success");
        assertTest.assertNotNull(from(json).get("id_company"));
        assertTest.assertAll();
    }

    public void createOrganizationWithWrongOwner(String companyName, String companyType, String companyUser1,
                                                      String companyUser2, String emailOwner) {
        requestBody.put("company_name", companyName);
        requestBody.put("company_type", companyType);
        requestBody.put("company_users", Arrays.asList(companyUser1, companyUser2));
        requestBody.put("email_owner", emailOwner);
        String json = given()
                .contentType("application/json")
                .body(requestBody)
                .when().post(companyUrl).then().log().body()
                .statusCode(200).extract().asString();
        assertTest.assertEquals(from(json).get("type"), "error");
        assertTest.assertEquals(from(json).get("message"), "Пользователь не найден c email_owner " + emailOwner);
        assertTest.assertAll();
    }

    public void createOrganizationWithoutUsers(String companyName, String companyType, String companyUser1,
                                                 String companyUser2, String emailOwner) {
        requestBody.put("company_name", companyName);
        requestBody.put("company_type", companyType);
        requestBody.put("company_users", Arrays.asList(companyUser1, companyUser2));
        requestBody.put("email_owner", emailOwner);
        String json = given()
                .contentType("application/json")
                .body(requestBody)
                .when().post(companyUrl).then().log().body()
                .statusCode(200).extract().asString();
        assertTest.assertEquals(from(json).get("type"), "error");
        assertTest.assertEquals(from(json).get("message"), " company_users  не указаны сотрудники");
        assertTest.assertAll();
    }
    public void createOrganizationWithWrongType(String companyName, String companyType, String companyUser1,
                                               String companyUser2, String emailOwner) {
        requestBody.put("company_name", companyName);
        requestBody.put("company_type", companyType);
        requestBody.put("company_users", Arrays.asList(companyUser1, companyUser2));
        requestBody.put("email_owner", emailOwner);
        String json = given()
                .contentType("application/json")
                .body(requestBody)
                .when().post(companyUrl).then().log().body()
                .statusCode(200).extract().asString();
        assertTest.assertEquals(from(json).get("type"), "error");
        assertTest.assertEquals(from(json).get("message"), " company_type "+companyType+" некорректный");
        assertTest.assertAll();
    }
}
