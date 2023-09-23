package edu.kmaooad.capstone23.orgs.jobs.controllers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import edu.kmaooad.capstone23.common.Result;
import java.util.HashMap;
import java.util.Map;
import jakarta.inject.Inject;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class DeleteJobControllerTest {

    @Inject
    CommandHandler<CreateJob, JobCreated> handler;
    @Test
    @DisplayName("Delete job: valid input")
    public void testBasicJobDeleting() {
        //CommandHandler<CreateJob, JobCreated> handler = null;

        CreateJob command = new CreateJob("Teacher", true);
        Result<JobCreated> result = handler.handle(command);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("jobId", result.getValue().getJobId().toHexString());

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/jobs/delete")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Delete job: invalid input")
    public void testJobDeletingWithInvalidInput() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        ObjectId id = new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa");
        jsonAsMap.put("jobId", id);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/jobs/delete")
                .then()
                .statusCode(400);
    }
}
