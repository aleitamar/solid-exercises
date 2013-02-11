package com.theladders.solid.ocp.resume;

import com.theladders.solid.ocp.jobseeker.JobseekerConfidentialityProfile;

public class ConfidentialResumeHandler
{
  public void makePhraseGroupNonConfidential(JobseekerConfidentialityProfile jobseekerConfidentialityProfile,
                                             ConfidentialPhraseGroup confidentialPhraseGroup)
  {
    boolean isChanged = jobseekerConfidentialityProfile.resetConfidentialFlagsForCategoryGroup(confidentialPhraseGroup);
    if (isChanged) { doSomething(); }
  }

  private void doSomething() {}
}
