package edu.kmaooad.capstone23.competences.commands;

import org.bson.types.ObjectId;

public class DeleteSkill {

    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
