package com.theladders.solid.srp.http;

import java.util.Map;

import com.theladders.solid.srp.jobseeker.Jobseeker;

public class HttpRequest
{
  private final HttpSession session;
  private final Map<String, String> parameters;

  public HttpRequest(HttpSession session,
                     Map<String,String> parameters)
  {
    this.session = session;
    this.parameters = parameters;
  }

  public HttpSession getSession()
  {
    return session;
  }
  
  public Jobseeker getJobseeker()
  {
	  return session.getJobseeker();
  }

  public String getParameter(String key)
  {
    return parameters.get(key);
  }
}
