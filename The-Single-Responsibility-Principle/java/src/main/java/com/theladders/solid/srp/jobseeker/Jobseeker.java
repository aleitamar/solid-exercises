package com.theladders.solid.srp.jobseeker;

public class Jobseeker
{
  private final JobseekerProfileManager jobseekerProfileManager;
  private final int id;
  private final boolean isPremium;

  public Jobseeker(int id, boolean isPremium, JobseekerProfileManager jobseekerProfileManager)
  {
    this.id = id;
    this.isPremium = isPremium;
    this.jobseekerProfileManager = jobseekerProfileManager;
  }

  public boolean isPremium()
  {
    return isPremium;
  }

  public JobseekerProfile getProfile()
  {
    return jobseekerProfileManager.getJobSeekerProfile(this);
  }

  public Boolean forcedToCompleteProfile()
  {
    getProfile();
    return getProfile().forceCompletion();
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