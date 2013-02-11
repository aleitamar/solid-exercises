package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.job.JobSearchService;

public class ApplyWithResumeParams extends ApplyParams
{
  public ApplyWithResumeParams(HttpRequest request, JobSearchService jobSearchService)
  {
    super(request, jobSearchService);
  }
}
