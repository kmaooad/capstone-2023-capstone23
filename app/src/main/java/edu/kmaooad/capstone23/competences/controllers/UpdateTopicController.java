package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.HandlingError;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.competences.commands.UpdateTopic;
import edu.kmaooad.capstone23.competences.events.TopicCreated;
import edu.kmaooad.capstone23.competences.events.TopicUpdated;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/competences/topic/update")
public class UpdateTopicController extends TypicalController<UpdateTopic, TopicUpdated> {
    @Inject
    CommandHandler<UpdateTopic, TopicUpdated> updateTopicHandler;

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(value = {
            @APIResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TopicUpdated.class)) }),
            @APIResponse(responseCode = "400", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = HandlingError.class)) }),
            @APIResponse(responseCode = "500")
    })
    public Response updateTopic(UpdateTopic command) {
        try {
            Result<TopicUpdated> result = updateTopicHandler.handle(command);

            if (!result.isSuccess()) {
                return Response.status(400).entity(result.toError()).build();
            }

            return Response.ok(result.getValue(), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }
}
