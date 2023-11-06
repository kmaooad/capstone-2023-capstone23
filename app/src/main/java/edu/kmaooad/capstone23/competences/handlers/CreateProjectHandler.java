package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateProj;
import edu.kmaooad.capstone23.competences.dal.Project;
import edu.kmaooad.capstone23.competences.events.ProjCreated;
import edu.kmaooad.capstone23.competences.services.ProjectService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateProjectHandler implements CommandHandler<CreateProj, ProjCreated> {
    @Inject
    ProjectService service; //intentionally left non-private: https://stackoverflow.com/questions/55101095/why-does-quarkus-warn-me-about-injection-in-private-fields

    @Override
    public Result<ProjCreated> handle(CreateProj command) {
        var proj = new Project();
        proj.name = command.getName();
        proj.description = command.getDescription();
        proj.skills = command.getSkills();
        proj.skillSets = command.getSkillSets();

        var insertedProj = service.insert(proj);

        if (insertedProj != null) {
            var event = new ProjCreated(proj.id);
            return new Result<>(event);
        } else {
            return new Result<>(ErrorCode.EXCEPTION,
                    "Trying to create an already existent project.");
        }
    }
}
