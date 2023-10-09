package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.CreateJobPref;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.dal.JobPreference;
import edu.kmaooad.capstone23.cvs.events.JobPrefCreated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateJobPrefHandler implements CommandHandler<CreateJobPref, JobPrefCreated> {

    @Inject
    CVRepository cvRepository;

    @Override
    public Result<JobPrefCreated> handle(CreateJobPref command) {
        JobPreference pref = new JobPreference();

        if (command.getLocation() == null ) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Location cannot be null");
        } else if (command.getLocation() != null && command.getLocation().isBlank()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Location cannot be blank");
        } else if(command.getLocation().length()<1 || command.getLocation().length()>100){
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Invalid size of location");
        }
        pref.industry = command.getIndustry();
        pref.location = command.getLocation();
        pref.category = command.getCategory();

        CV cv = cvRepository.findById(command.getCvId());
        cv.preference = pref;
        cvRepository.update(cv);

        JobPrefCreated result = new JobPrefCreated(cv.id);
        return new Result<>(result);
    }

}
