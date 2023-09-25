package edu.kmaooad.capstone23.activities.controllers;

import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RequestToJoinExtraActControllerTest {

    private String idToUpdate;

    @Inject
    ExtracurricularActivityRepository extraActRepository;

    @BeforeEach
    void setUp() {
        ExtracurricularActivity extracurricularActivity = new ExtracurricularActivity();

        extracurricularActivity.extracurricularActivityName = "Initial Extracurricular Activity";
        extraActRepository.insert(extracurricularActivity);

        idToUpdate = extracurricularActivity.id.toString();
    }


    @Test
    @DisplayName("Create Request to Join Activity: Basic")
    public void testBasicRequestToJoinAct() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        String userName = "person1";
        String extraActId = idToUpdate;

        jsonAsMap.put("userName", userName);
        jsonAsMap.put("extraActId", extraActId);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/extracurricularActivity/request")
                .then()
                .statusCode(200);
    }
}