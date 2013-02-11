package com.theladders.solid.ocp.resume;

import java.util.List;

import com.theladders.solid.ocp.jobseeker.JobseekerConfidentialityProfile;

public class ConfidentialPhraseGroupManager
{
  public JobseekerConfidentialityProfile jobseekerConfidentialityProfile;
  
  public ConfidentialPhraseGroupManager(JobseekerConfidentialityProfile jobseekerConfidentialityProfile)
  {
    this.jobseekerConfidentialityProfile = jobseekerConfidentialityProfile;
  }

  public boolean makePhraseGroupPublic(ConfidentialPhraseGroup confidentialPhraseGroup)
  {
    boolean isChanged = false;
    for(ConfidentialPhraseItem confidentailPhraseItem : confidentialPhraseGroup.getConfidentialPhrases())
    {
      List<ConfidentialPhrase> phrases = jobseekerConfidentialityProfile.getConfidentialPhrases(confidentailPhraseItem);
      for (ConfidentialPhrase phrase : phrases)
      {
        isChanged = phrase.setConfidential(false);
      }
    }
    return isChanged;
  }

}
