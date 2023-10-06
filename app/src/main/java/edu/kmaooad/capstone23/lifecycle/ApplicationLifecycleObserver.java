package edu.kmaooad.capstone23.lifecycle;

import com.mongodb.client.model.IndexOptions;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.users.dal.repositories.UserRepository;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.bson.Document;

@ApplicationScoped
public class ApplicationLifecycleObserver {
    @Inject
    MembersRepository membersRepository;
    @Inject
    UserRepository usersRepository;

    void onStart(@Observes StartupEvent ev) {
        // Ensure uniqueness of User email
        Document indexMember = new Document("email", 1);
        IndexOptions options = new IndexOptions().unique(true);
        membersRepository.mongoCollection().createIndex(indexMember, options);
        // delete previously duplicated test data
        usersRepository.delete("email", "john.doe@mail.com");
        Document indexUser = new Document("email", 2);
        usersRepository.mongoCollection().createIndex(indexUser, options);
    }
}