package edu.kmaooad.capstone23.jobs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.jobs.commands.UpdateJob;
import edu.kmaooad.capstone23.jobs.dal.Job;
import edu.kmaooad.capstone23.jobs.dal.JobsRepository;
import edu.kmaooad.capstone23.jobs.events.JobUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class UpdateJobHandler  implements CommandHandler<UpdateJob, JobUpdated> {
    @Inject
    private JobsRepository repository;

    public Result<JobUpdated> handle(UpdateJob command) {

        Job jobToUpdate = repository.findById(new ObjectId(command.getJobId()));

        if (jobToUpdate == null) {
            //TODO: Handle the case where the job doesn't exist
            // You can return an error Result or throw an exception
        }

        // Update job properties
        jobToUpdate.name = command.getJobName();
        jobToUpdate.description = command.getJobDescription();

        // Save the changes
        repository.update(jobToUpdate);
        JobUpdated result = new JobUpdated(jobToUpdate.getId().toString());

        return new Result<JobUpdated>(result);
    }
}
