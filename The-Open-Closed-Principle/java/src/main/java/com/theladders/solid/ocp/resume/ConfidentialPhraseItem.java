package com.theladders.solid.ocp.resume;

public enum ConfidentialPhraseItem
{
  Name(76),
  MailingAddress(79),
  PhoneNumber(78),
  EmailAddress(77),
  CompanyName(81),
  WorkExperience(82);

  @SuppressWarnings("unused")
  private int id;

  private ConfidentialPhraseItem(int id)
  {
    this.id = id;
  }
  
  
  
}
