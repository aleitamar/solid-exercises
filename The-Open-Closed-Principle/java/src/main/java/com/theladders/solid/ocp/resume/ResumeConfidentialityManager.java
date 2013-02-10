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

  public void makePhraseGroupNonConfidential(JobseekerConfidentialityProfile jobseekerConfidentialityProfile,
                                             ConfidentialPhraseGroup confidentailPhraseCategoryGroup)
  {
    confidentialResumeHandler.makePhraseGroupNonConfidential(jobseekerConfidentialityProfile,
                                                             confidentailPhraseCategoryGroup);
  }

  public boolean isConfidential(User user, ConfidentialPhraseItem confidentialPhraseItem)
  {
    return confidentialResumeHandler.isConfidential(user, confidentialPhraseItem);
  }
}
