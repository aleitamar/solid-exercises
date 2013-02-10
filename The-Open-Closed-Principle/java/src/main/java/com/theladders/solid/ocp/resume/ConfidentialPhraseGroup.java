package com.theladders.solid.ocp.resume;

import java.util.ArrayList;
import java.util.List;

public enum ConfidentialPhraseGroup
{
  ALL("all"),
  CONTACT_INFO("contact_info");
  
  private String key;  
  
  ConfidentialPhraseGroup(String key)
  {
    this.key = key;
  }
  
  public List<ConfidentialPhraseItem> getConfidentialPhrases()
  {
    List<ConfidentialPhraseItem> confidentialPhraseItems = new ArrayList<ConfidentialPhraseItem>(); 
    switch(key)
    {
    case "all":
      confidentialPhraseItems.add(ConfidentialPhraseItem.Name);
      confidentialPhraseItems.add(ConfidentialPhraseItem.MailingAddress);
      confidentialPhraseItems.add(ConfidentialPhraseItem.PhoneNumber);
      confidentialPhraseItems.add(ConfidentialPhraseItem.EmailAddress);
      confidentialPhraseItems.add(ConfidentialPhraseItem.CompanyName);
      confidentialPhraseItems.add(ConfidentialPhraseItem.WorkExperience);
      break;
    case "contact_info":
      confidentialPhraseItems.add(ConfidentialPhraseItem.MailingAddress);
      confidentialPhraseItems.add(ConfidentialPhraseItem.PhoneNumber);
      confidentialPhraseItems.add(ConfidentialPhraseItem.EmailAddress);
      break;
    }
    return confidentialPhraseItems;
  }
  
  
}
