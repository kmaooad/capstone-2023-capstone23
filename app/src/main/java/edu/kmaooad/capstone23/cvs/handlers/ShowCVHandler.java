package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.ShowCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CV.Visibility;
import edu.kmaooad.capstone23.cvs.events.CVUpdated;
import edu.kmaooad.capstone23.cvs.services.CVService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;


@RequestScoped
public class ShowCVHandler implements CommandHandler<ShowCV, CVUpdated> {

    @Inject
    CVService cvService;

    @Override
    public Result<CVUpdated> handle(ShowCV command) {
        CV cv = cvService.findById(command.getCvId());

        if (cv == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Illegal cv id");
        }

        cv.visibility =Visibility.VISIBLE;

        cvService.update(cv);

        CVUpdated result = new CVUpdated(cv.id);
        return new Result<>(result);
    }
}
