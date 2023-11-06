package edu.kmaooad.capstone23.orgs.services;

import edu.kmaooad.capstone23.orgs.dal.RequestsRepository;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.Request;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class OrgService {
    @Inject
    private OrgsRepository orgsRepository;

    public Org createOrg(String name, String description, String industry, String website, String emailDomain) {
        Org org = new Org();
        org.name = name;
        org.description = description;
        org.jobs = new ArrayList<>();
        org.industry = industry;
        org.website = website;
        org.emailDomain = emailDomain;
        org.isActive=true;
        return orgsRepository.insert(org);
    }

    public Org getOrgByName(String name) {
        return orgsRepository.findByName(name);
    }

    public Org getOrgById(String id) {
        return orgsRepository.findById(id);
    }

    public void deleteOrg(Org department) {
        orgsRepository.delete(department);
    }

    public void deleteOrgById(String id) {
        orgsRepository.deleteById(new ObjectId(id));
    }

    public void updateOrg(Org department) {
        orgsRepository.update(department);
    }


    public void deleteAllOrgs() {
        orgsRepository.deleteAll();
    }

//Request repo interact block
@Inject
private RequestsRepository requestsRepository;

    public Request findRequestById(String id) {
        return requestsRepository.findById(id);
    }

    public Request insertRequest(Request request){
        return requestsRepository.insert(request);
    }

    public void deleteRequest(Request request) {
        requestsRepository.deleteById(request.id);
    }
}