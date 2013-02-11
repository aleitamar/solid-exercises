package com.theladders.solid.srp;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.job.JobSearchService;

public class ApplyWithFileParams extends ApplyParams
{
  public ApplyWithFileParams(HttpRequest request,
                             JobSearchService jobSearchService)
  {
    super(request, jobSearchService);
  }


  public boolean shouldMakeResumeActive()
  {
    return "yes".equals(request.getParameter("makeResumeActive"));
  }


  public Map<String, Boolean> resumeOptions()
  {
    Map<String, Boolean> options = new HashMap<>();
    options.put("shouldMakeResumeActive", shouldMakeResumeActive());
    return options;
  }
}
