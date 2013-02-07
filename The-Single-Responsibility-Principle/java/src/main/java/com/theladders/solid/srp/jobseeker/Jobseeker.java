package com.theladders.solid.srp.jobseeker;

import java.util.Map;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;

public class Jobseeker
{
  private final JobseekerProfile jobseekerProfile;
  private final int id;
  private final boolean hasPremiumAccount;

  public Jobseeker(int id, boolean hasPremiumAccount, JobseekerProfileManager jobseekerProfileManager)
  {
    this.id = id;
    this.hasPremiumAccount = hasPremiumAccount;
    this.jobseekerProfile = jobseekerProfileManager.getJobSeekerProfile(this);
  }
  
  public void apply(Job job, String fileName, Map<String, Boolean> resumeOptions, JobApplicationSystem jobApplicationSystem, MyResumeManager myResumeManager)
  {
	Resume resume = myResumeManager.findOrCreateResumeWithOptions(this, resumeOptions, fileName);
    UnprocessedApplication application = new UnprocessedApplication(this, job, resume);
    JobApplicationResult applicationResult = jobApplicationSystem.apply(application);
    
    if (applicationResult.failure())
    {
      throw new ApplicationFailureException(applicationResult.toString());
    }
  }

  public boolean isPremium()
  {
    return hasPremiumAccount;
  }
  
  public boolean forcedToCompleteProfile(JobseekerProfile profile){
    return !this.hasPremiumAccount && profile.needsCompletion();
  }
  
  public JobseekerProfile getProfile()
  {
	  return jobseekerProfile;
  }

  public int getId()
  {
    return id;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Jobseeker other = (Jobseeker) obj;
    if (id != other.id)
      return false;
    return true;
  }
}
