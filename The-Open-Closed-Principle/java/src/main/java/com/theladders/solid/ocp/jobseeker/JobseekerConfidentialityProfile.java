package com.theladders.solid.ocp.jobseeker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.ocp.resume.ConfidentialPhrase;
import com.theladders.solid.ocp.resume.ConfidentialPhraseItem;
import com.theladders.solid.ocp.resume.ConfidentialPhraseGroup;

public class JobseekerConfidentialityProfile
{
  private Map<String, List<ConfidentialPhrase>> confidentialityProfile;

  public JobseekerConfidentialityProfile()
  {
    confidentialityProfile = new HashMap<>();
  }
  

  public boolean resetConfidentialFlagsForCategoryGroup(ConfidentialPhraseGroup categoryGroup)
  {
    boolean isChanged = false;

    for(ConfidentialPhraseItem confidentailPhraseItem : categoryGroup.getConfidentialPhrases())
    {
      List<ConfidentialPhrase> phrases = this.getConfidentialPhrases(confidentailPhraseItem);
      if (phrases != null)
      {
        for (ConfidentialPhrase phrase : phrases)
        {
          if (phrase.isConfidential())
          {
            phrase.setConfidential(false);
            isChanged = true;
          }
        }
      }
    }
    return isChanged;
  }

  public boolean isConfidential(ConfidentialPhraseItem confidentialPhraseItem)
  {	  
    boolean isConfidential = false;


    List<ConfidentialPhrase> phrases = this.getConfidentialPhrases(confidentialPhraseItem);
    if (phrases != null)
    {
      for (ConfidentialPhrase phrase : phrases)
      {
        if (phrase.isConfidential())
        {
          isConfidential = true;
        }
      }
    }  
    return isConfidential;
  }

  public void setConfidential(ConfidentialPhraseItem confidentialPhraseItem)
  {
    ConfidentialPhrase phrase = new ConfidentialPhrase();
    phrase.setConfidential(true);
    List<ConfidentialPhrase> phrases = new ArrayList<ConfidentialPhrase>();
    phrases.add(phrase);
    confidentialityProfile.put(confidentialPhraseItem.name(), phrases);
  }

  private List<ConfidentialPhrase> getConfidentialPhrases(ConfidentialPhraseItem item)
  {
    return confidentialityProfile.get(item.name());
  }
}
