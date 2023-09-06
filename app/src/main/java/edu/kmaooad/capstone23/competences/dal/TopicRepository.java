package edu.kmaooad.capstone23.competences.dal;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TopicRepository implements PanacheMongoRepository<Topic> {
    public Topic findById(String id) {
        return find("_id", id).firstResult();
    }

    public void insert(Topic topic) {
        persist(topic);
    }
}