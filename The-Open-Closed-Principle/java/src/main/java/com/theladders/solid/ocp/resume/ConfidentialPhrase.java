package com.theladders.solid.ocp.resume;

public class ConfidentialPhrase
{
  private boolean isConfidential;
  private String name;
  
  public ConfidentialPhrase(String name)
  {
	  this.name = name;
  }

  public boolean isConfidential()
  {
    return isConfidential;
  }

  public void setConfidential(boolean isConfidential)
  {
    this.isConfidential = isConfidential;
  }
}
