package com.theladders.solid.srp.job.application;

import java.util.ArrayList;
import java.util.List;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.jobseeker.Jobseeker;

public class JobApplicationRepository
{
  private final List<SuccessfulApplication> applications;


  public JobApplicationRepository()
  {
    this.applications = new ArrayList<>();
  }


  public Boolean add(SuccessfulApplication application)
  {
    if (!applicationExistsFor((Jobseeker) application.getJobseeker(), (Job) application.getJob()))
    {
      applications.add(application);
      return true;
    }
    return false;
  }


  public boolean applicationExistsFor(Jobseeker jobseeker,
                                      Job job)
  {
    for (SuccessfulApplication application : applications)
    {
      if (application.getJobseeker().equals(jobseeker) && application.getJob().equals(job))
      {
        return true;
      }
    }

    return false;
  }
}
