package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AddLogoControllerTest {
    private String idToUpdate;

    @Inject
    DepartmentsRepository departmentsRepository;

    @BeforeEach
    void setUp() {
        Department department = new Department();
        department.name = "Initial Department";
        department.description = "Initial Department Description";
        department.parent = "NaUKMA";
        department.members = new ArrayList<>();
        departmentsRepository.insert(department);

        idToUpdate = department.id.toString();
    }

    @Test
    @DisplayName("Add Logo: Basic")
    public void testBasicAddLogo() {
        Map<String, Object> jsonAsMap = new HashMap<>();


        jsonAsMap.put("departmentId", idToUpdate);

        String logo = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAYAA";

        jsonAsMap.put("logo", logo);

        String logoName = "img.png";

        jsonAsMap.put("logoName", logoName);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/departments/add-logo")
                .then()
                .statusCode(200);
    }


    @Test
    @DisplayName("Add Logo: Invalid Department Id")
    public void testInvalidDepartmentIdAddLogo() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        String departmentId = "64fbb243275c1111167b87a3";

        jsonAsMap.put("departmentId", departmentId);

        String logo = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAYAA";

        jsonAsMap.put("logo", logo);

        String logoName = "img.png";

        jsonAsMap.put("logoName", logoName);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/departments/add-logo")
                .then()
                .statusCode(400);
    }
}