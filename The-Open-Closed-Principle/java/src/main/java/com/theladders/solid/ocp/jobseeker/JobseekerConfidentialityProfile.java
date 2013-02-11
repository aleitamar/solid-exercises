package com.theladders.solid.ocp.jobseeker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.ocp.resume.ConfidentialPhrase;
import com.theladders.solid.ocp.resume.ConfidentialPhraseGroupManager;
import com.theladders.solid.ocp.resume.ConfidentialPhraseItem;
import com.theladders.solid.ocp.resume.ConfidentialPhraseGroup;
import com.theladders.solid.ocp.resume.ConfidentialPhraseManager;

public class JobseekerConfidentialityProfile
{
  private Map<String, List<ConfidentialPhrase>> confidentialityProfile;
  private ConfidentialPhraseGroupManager confidentialPhraseGroupManager;
  private ConfidentialPhraseManager confidentialPhraseManager;

  public JobseekerConfidentialityProfile()
  {
    confidentialityProfile = new HashMap<>();
    confidentialPhraseGroupManager = new ConfidentialPhraseGroupManager(this);
    confidentialPhraseManager = new ConfidentialPhraseManager(this);
  }
  
  public boolean resetConfidentialFlagsForCategoryGroup(ConfidentialPhraseGroup confidentialPhraseGroup)
  {
    return confidentialPhraseGroupManager.makePhraseGroupPublic(confidentialPhraseGroup);
  }

  // Used for test
  public boolean isConfidential(ConfidentialPhraseItem confidentialPhraseItem)
  {	  
    return confidentialPhraseManager.isPhaseGroupItemConfidential(confidentialPhraseItem);
  }

  // Used for test
  public void setConfidential(ConfidentialPhraseItem confidentialPhraseItem)
  {
    ConfidentialPhrase phrase = new ConfidentialPhrase();
    phrase.setConfidential(true);
    List<ConfidentialPhrase> phrases = new ArrayList<ConfidentialPhrase>();
    phrases.add(phrase);
    confidentialityProfile.put(confidentialPhraseItem.name(), phrases);
  }

  public List<ConfidentialPhrase> getConfidentialPhrases(ConfidentialPhraseItem item)
  {
    return confidentialityProfile.get(item.name());
  }
}
