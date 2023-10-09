package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class SetHiringStatusOffControllerTest {
    private String idToUpdate;

    @Inject
    DepartmentsRepository departmentsRepository;

    @BeforeEach
    void setUp() {
        departmentsRepository.deleteAll();
        Department department = new Department();
        department.name = "Initial Department";
        department.description = "Initial Department Description";
        department.parent = "NaUKMA";
        department.members = new ArrayList<>();
        departmentsRepository.insert(department);

        idToUpdate = department.id.toString();
    }


    @Test
    @DisplayName("Set hiring status off")
    void setHiringStatusOff() {
        Map<String, String> jsonAsMap = new HashMap<>();
        jsonAsMap.put("departmentId", idToUpdate);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when().post("/departments/set-hiring-status-off")
                .then()
                .statusCode(200);
    }


    @Test
    @DisplayName("Set hiring status off with wrong id")
    void setHiringStatusOffWithWrongId() {
        Map<String, String> jsonAsMap = new HashMap<>();
        jsonAsMap.put("departmentId", "64fbb243275c1111167b87a3");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when().post("/departments/set-hiring-status-off")
                .then()
                .statusCode(400);
    }
}