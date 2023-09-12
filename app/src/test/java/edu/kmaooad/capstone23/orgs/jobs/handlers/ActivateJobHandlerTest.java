package edu.kmaooad.capstone23.orgs.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.ActivateJob;
import edu.kmaooad.capstone23.jobs.commands.CreateJob;
import edu.kmaooad.capstone23.jobs.events.JobActivated;
import edu.kmaooad.capstone23.jobs.events.JobCreated;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;
@QuarkusTest
public class ActivateJobHandlerTest {
    @Inject
    CommandHandler<CreateJob, JobCreated> handler;
    @Inject
    CommandHandler<ActivateJob, JobActivated> handlerForActivation;
    @Test
    void testSuccessfulHandling() {
//        Map<String, Object> jsonAsMap = new HashMap<>();
//        ObjectId id = new ObjectId("64faf6ad341e202c91f76c83");
//        jsonAsMap.put("_id", id);

        CreateJob command = new CreateJob("IT teacher", true);

        Result<JobCreated> result = handler.handle(command);

        ActivateJob commandForAct = new ActivateJob();
        commandForAct.setJobId(result.getValue().getJobId());

        Result<JobActivated> resultForAct = handlerForActivation.handle(commandForAct);

        Assertions.assertTrue(resultForAct.isSuccess());
        Assertions.assertNotNull(resultForAct.getValue());
        Assertions.assertTrue(resultForAct.getValue().getJobId().equals(result.getValue().getJobId()));


    }

}
