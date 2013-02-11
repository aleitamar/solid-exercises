package com.theladders.solid.ocp.resume;

public class ConfidentialPhrase
{
  private boolean isConfidential;

  public ConfidentialPhrase()
  {
  }

  public boolean isConfidential()
  {
    return isConfidential;
  }

  public boolean setConfidential(boolean isConfidential)
  {
    boolean isChanged = !(isConfidential == this.isConfidential);
    this.isConfidential = isConfidential;
    return isChanged;
  }
}
