package edu.kmaooad.capstone23.jobs.commands;


import jakarta.validation.constraints.NotBlank;

public class ActivateJob {

    @NotBlank
    private String jobId;

    public String getJobId() {return jobId;}

    public void setJobId(String jobId) { this.jobId = jobId;}
}
