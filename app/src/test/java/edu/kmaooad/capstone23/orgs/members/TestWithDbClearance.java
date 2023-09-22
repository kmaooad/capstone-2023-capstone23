package edu.kmaooad.capstone23.orgs.members;

import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;

public class TestWithDbClearance {
    @Inject
    protected MembersRepository membersRepository;

    @Inject
    protected OrgsRepository orgsRepository;

    @AfterEach
    void clearMemberCollection() {
        membersRepository.deleteAll();
        orgsRepository.deleteAll();
    }
}