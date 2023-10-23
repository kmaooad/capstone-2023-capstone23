package edu.kmaooad.capstone23.cvs.services;

import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CVService {

    @Inject
    CVRepository cvRepository;

    public CV create(CV cv){
        cvRepository.persist(cv);
        return cv;
    }


}
