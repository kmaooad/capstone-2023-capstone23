package edu.kmaooad.capstone23.competences.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.DeleteTopic;
import edu.kmaooad.capstone23.competences.dal.Topic;
import edu.kmaooad.capstone23.competences.events.TopicDeleted;
import edu.kmaooad.capstone23.competences.services.TopicService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@RequestScoped
public class DeleteTopicHandler implements CommandHandler<DeleteTopic, TopicDeleted> {
    @Inject
    TopicService service;

    public Result<TopicDeleted> handle(DeleteTopic command) {
        Optional<Topic> topic = service.findById(command.getId());
        if (topic.isEmpty()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Topic with this ID does not exist");
        }

        List<Topic> children = service.findByParentId(command.getId());
        if (!children.isEmpty()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Topic has children. Delete the children first.");
        }

        service.delete(topic.get());

        return new Result<>(new TopicDeleted(command.getId()));
    }
}
