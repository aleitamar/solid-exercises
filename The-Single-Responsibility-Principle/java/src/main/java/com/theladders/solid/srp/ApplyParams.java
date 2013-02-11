package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.jobseeker.Jobseeker;

public abstract class ApplyParams
{
  protected HttpRequest      request;
  protected JobSearchService jobSearchService;


  public ApplyParams(HttpRequest request,
                     JobSearchService jobSearchService)
  {
    this.request = request;
    this.jobSearchService = jobSearchService;
  }


  public Jobseeker getJobseeker()
  {
    return request.getJobseeker();
  }


  public String getJobId()
  {
    return request.getParameter("jobId");
  }


  public Job getJob()
  {
    return jobSearchService.getJob(Integer.parseInt(getJobId()));
  }
}
