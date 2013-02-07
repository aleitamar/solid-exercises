package com.theladders.solid.srp.http;

import com.theladders.solid.srp.jobseeker.Jobseeker;

// responsibilities
// - represents a users session in the system. Allows access to user information

public class HttpSession
{
  private final Jobseeker jobseeker;

  public HttpSession(Jobseeker jobseeker)
  {
    this.jobseeker = jobseeker;
  }

  public Jobseeker getJobseeker()
  {
    return jobseeker;
  }
}
