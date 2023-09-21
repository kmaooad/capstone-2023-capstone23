package edu.kmaooad.capstone23.activities.commands;
import org.bson.types.ObjectId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateExtraActivity {
    @NotNull
    private ObjectId id;
    @NotBlank
    @Size(max = 100)
    private String name;

    public ObjectId getId() { return id; }

    public void setId(ObjectId id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}