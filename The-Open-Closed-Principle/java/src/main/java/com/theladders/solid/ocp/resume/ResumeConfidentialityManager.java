package com.theladders.solid.ocp.resume;

import com.theladders.solid.ocp.jobseeker.JobseekerConfidentialityProfile;
import com.theladders.solid.ocp.user.User;

public class ResumeConfidentialityManager
{
  private final ConfidentialResumeHandler confidentialResumeHandler;

  public ResumeConfidentialityManager(ConfidentialResumeHandler confidentialResumeHandler)
  {
    this.confidentialResumeHandler = confidentialResumeHandler;
  }

  public void makeAllContactInfoNonConfidential(JobseekerConfidentialityProfile jobseekerConfidentialityProfile)
  {
    confidentialResumeHandler.makeAllContactInfoNonConfidential(jobseekerConfidentialityProfile);
  }

  public void makeAllCategoriesNonConfidential(JobseekerConfidentialityProfile jobseekerConfidentialityProfile)
  {
    confidentialResumeHandler.makeAllCategoriesNonConfidential(jobseekerConfidentialityProfile);
  }
  
  public boolean isConfidential(User user, ConfidentialPhraseCategory confidentialPhraseCategory)
  {
	return confidentialResumeHandler.isConfidential(user, confidentialPhraseCategory);
  }
}
