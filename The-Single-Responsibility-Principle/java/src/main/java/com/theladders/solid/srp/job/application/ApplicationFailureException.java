package com.theladders.solid.srp.job.application;

public class ApplicationFailureException extends RuntimeException
{
  private static final long serialVersionUID = 7526472295622776147L;  
  
  public ApplicationFailureException(String reason)
  {
    super(reason);
  }
}
