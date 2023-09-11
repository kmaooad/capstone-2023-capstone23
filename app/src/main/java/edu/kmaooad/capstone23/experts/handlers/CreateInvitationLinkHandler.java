package edu.kmaooad.capstone23.experts.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.experts.commands.CreateInvitationLink;
import edu.kmaooad.capstone23.experts.dal.ExpertInvitation;
import edu.kmaooad.capstone23.experts.dal.ExpertInvitationRepository;
import edu.kmaooad.capstone23.experts.events.InvitationLinkCreated;
import edu.kmaooad.capstone23.experts.service.ExpertService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class CreateInvitationLinkHandler implements CommandHandler<CreateInvitationLink, InvitationLinkCreated> {

    @Inject
    ExpertInvitationRepository repository;
    @Inject
    ExpertService expertService;

    @Override
    public Result<InvitationLinkCreated> handle(CreateInvitationLink command) {
        var expertInvitation = new ExpertInvitation();
        repository.persist(expertInvitation);

        return null;
    }
}
