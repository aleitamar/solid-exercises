package com.theladders.solid.ocp.jobseeker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.ocp.resume.ConfidentialPhrase;
import com.theladders.solid.ocp.resume.ConfidentialPhraseCategory;

public class JobseekerConfidentialityProfile
{
  private Map<String, List<ConfidentialPhrase>> confidentialityProfile;

  public JobseekerConfidentialityProfile()
  {
    confidentialityProfile = new HashMap<>();
  }

  public boolean resetConfidentialFlagsForCategory(ConfidentialPhraseCategory category)
  {
    boolean isChanged = false;

    List<ConfidentialPhrase> phrases = this.getConfidentialPhrases(category);
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

    return isChanged;
  }
  
  public boolean isConfidential(ConfidentialPhraseCategory confidentialPhraseCategory)
  {	  
    boolean isConfidential = false;
    

    List<ConfidentialPhrase> phrases = this.getConfidentialPhrases(confidentialPhraseCategory);
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
  
  public void setConfidential(ConfidentialPhraseCategory confidentialPhraseCategory)
  {
	  ConfidentialPhrase phrase = new ConfidentialPhrase("foo");
	  phrase.setConfidential(true);
	  List<ConfidentialPhrase> phrases = new ArrayList();
	  phrases.add(phrase);
	  confidentialityProfile.put(confidentialPhraseCategory.name(), phrases);
  }

  private List<ConfidentialPhrase> getConfidentialPhrases(ConfidentialPhraseCategory category)
  {
    return confidentialityProfile.get(category.name());
  }
}
