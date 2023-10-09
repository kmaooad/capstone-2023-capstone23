package edu.kmaooad.capstone23.feed_back.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.feed_back.commands.DeleteFeedBack;
import edu.kmaooad.capstone23.feed_back.dal.FeedBack;
import edu.kmaooad.capstone23.feed_back.dal.FeedBackRepository;
import edu.kmaooad.capstone23.feed_back.events.FeedBackDeleted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class DeleteFeedBackHandler implements CommandHandler<DeleteFeedBack, FeedBackDeleted> {


    @Inject
    private FeedBackRepository repository;


    @Override
    public Result<FeedBackDeleted> handle(DeleteFeedBack command) {
        ObjectId id = command.getId();
        FeedBack feedBack = repository.findById(id);

        if (feedBack == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Feedback not found");
        }

        repository.delete(feedBack);

        return new Result<>(new FeedBackDeleted(feedBack));
    }
}
