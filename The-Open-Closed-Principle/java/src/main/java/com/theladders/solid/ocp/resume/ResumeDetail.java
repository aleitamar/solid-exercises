package com.theladders.solid.ocp.resume;

public abstract class ResumeDetail extends ResumeSensitiveInformation
{
  protected String label;
  
  public String getName()
  {
    return label;
  }
}
