package edu.kmaooad.capstone23.projs.events;
import java.util.List;
public class ProjCreated {
    private String projId;

    public String getProjId() {
        return projId;
    }
    public ProjCreated(String projId) {
        this.projId = projId;
    }
}
