package com.theladders.solid.ocp.resume;

public abstract class ResumeSensitiveInformation
{
  private boolean isConfidential = false;
  
  public boolean isConfidential()
  {
    return isConfidential;
  }
  
  public void setConfidential()
  {
    isConfidential = true;
  }
  
  public void setPublic()
  {
    isConfidential = false;
  }

}