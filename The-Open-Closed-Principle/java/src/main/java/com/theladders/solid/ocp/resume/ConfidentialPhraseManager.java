package com.theladders.solid.ocp.resume;

import java.util.List;

import com.theladders.solid.ocp.jobseeker.JobseekerConfidentialityProfile;

public class ConfidentialPhraseManager
{

  private JobseekerConfidentialityProfile jobseekerConfidentialityProfile;

  public ConfidentialPhraseManager(JobseekerConfidentialityProfile jobseekerConfidentialityProfile)
  {
    this.jobseekerConfidentialityProfile = jobseekerConfidentialityProfile;
  }
  
  public boolean isPhaseGroupItemConfidential(ConfidentialPhraseItem confidentialPhraseItem)
  {
    boolean isConfidential = false;
    List<ConfidentialPhrase> phrases = jobseekerConfidentialityProfile.getConfidentialPhrases(confidentialPhraseItem);
    if (phrases != null)
    {
      for (ConfidentialPhrase phrase : phrases)
      {
        isConfidential = phrase.isConfidential();
      }
    }  
    return isConfidential;
  }
}
