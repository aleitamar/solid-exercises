package com.theladders.solid.srp;

import java.util.HashMap;
import java.util.Map;

// responsibilities
// - provides an interface for the parameters provided to the apply action of the apply controller

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.jobseeker.Jobseeker;

public class ApplyParams {
  private HttpRequest request;
  private JobSearchService jobSearchService;
  
  public ApplyParams(HttpRequest request, JobSearchService jobSearchService)
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

  public Boolean isNewResume()
  {
    return !"existing".equals(request.getParameter("whichResume")); 
  }

  public Boolean shouldMakeResumeActive()
  {
    return "yes".equals(request.getParameter("makeResumeActive"));
  }

  public Map<String, Boolean> resumeOptions()
  {
    Map<String, Boolean> options = new HashMap<>();
    options.put("isNewResume", isNewResume());
    options.put("shouldMakeResumeActive", shouldMakeResumeActive());
    return options;
  }

}
