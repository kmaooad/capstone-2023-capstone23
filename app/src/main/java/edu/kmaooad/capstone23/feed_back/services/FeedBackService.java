package edu.kmaooad.capstone23.feed_back.services;
import edu.kmaooad.capstone23.feed_back.dal.FeedBack;


import java.util.Optional;

public interface FeedBackService {
    public Optional<FeedBack> findById(String id);

    public FeedBack insert(FeedBack feedBack);

}

