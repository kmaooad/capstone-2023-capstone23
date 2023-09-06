package edu.kmaooad.capstone23.competences.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.competences.commands.UpdateTopic;
import edu.kmaooad.capstone23.competences.events.TopicUpdated;
import jakarta.ws.rs.Path;

@Path("/topics/update")
public class UpdateTopicController extends TypicalController<UpdateTopic, TopicUpdated> {
}
