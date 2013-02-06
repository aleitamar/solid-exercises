package com.theladders.solid.srp.jobseeker;

public class Jobseeker
{
  public final JobseekerProfile profile;
  private final int id;
  private final boolean hasPremiumAccount;
  private final JobseekerProfileManager jobseekerProfileManager;

  public Jobseeker(int id, boolean hasPremiumAccount, JobseekerProfileManager jobseekerProfileManager)
  {
    this.id = id;
    this.hasPremiumAccount = hasPremiumAccount;
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.profile = jobseekerProfileManager.getJobSeekerProfile(this);
  }


  public boolean isPremium()
  {
    return hasPremiumAccount;
  }
  
  public boolean forcedToCompleteProfile(JobseekerProfile profile){
    return !this.hasPremiumAccount && profile.needsCompletion();
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
