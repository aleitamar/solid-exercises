package com.theladders.solid.srp.jobseeker;

public class JobseekerProfile
{
  private final Jobseeker jobseeker;
  private final ProfileStatus status;

  public JobseekerProfile(Jobseeker jobseeker, ProfileStatus status)
  {
    this.status = status;
    this.jobseeker = jobseeker;
  }

  public ProfileStatus getStatus()
  {
    return status;
  }

  public int getJobseekerId()
  {
    return jobseeker.getId();
  }

  public Boolean forceCompletion()
  {
    return needsCompletion() && !jobseeker.isPremium();
  }

  public boolean needsCompletion()
  {
    return (getStatus().equals(ProfileStatus.INCOMPLETE) ||
        getStatus().equals(ProfileStatus.NO_PROFILE) ||
        getStatus().equals(ProfileStatus.REMOVED));
  }
}
