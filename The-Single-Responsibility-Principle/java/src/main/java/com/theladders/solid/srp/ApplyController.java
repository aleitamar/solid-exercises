package com.theladders.solid.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.http.HttpResponseBroker;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.ResumeManager;

public class ApplyController
{
  private final JobSearchService        jobSearchService;
  private final JobApplicationSystem    jobApplicationSystem;
  private final MyResumeManager         myResumeManager;
  private final HttpResponseBroker      responseBroker;
  
  //Constructor
  public ApplyController(JobSearchService jobSearchService,
                         JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {
    this.jobSearchService = jobSearchService;
    this.jobApplicationSystem = jobApplicationSystem;
    this.myResumeManager = myResumeManager;
    this.responseBroker = new HttpResponseBroker();
  }

  public HttpResponse handle(HttpRequest request, HttpResponse response, String origFileName)
  {
	ApplyParams applyParams  = new ApplyParams(request, jobSearchService);
    Jobseeker jobseeker      = applyParams.getJobseeker();
    Job job                  = applyParams.getJob();    
    if (job == null)
    {
      return provideInvalidJobResponse(response, request.getParameter("jobId"));
    }
    try { jobseeker.apply(job, origFileName, applyParams.resumeOptions(), jobApplicationSystem, myResumeManager); }
    catch (Exception e) { return respondOnApplicationError(response, job); }
    
    return respondOnApplicationSuccess(jobseeker, job, response, responseBroker);
  }
  
  private HttpResponse respondOnApplicationError(HttpResponse response, Job job)
  {
    List<String> errList = new ArrayList<>();
    errList.add(jobApplicationSystem.getJobApplicationFailedMessage());
    responseBroker.provideResponseWithList(response, job.toMap(), "error", errList);
    return response;
  }
  
  private HttpResponse respondOnApplicationSuccess(Jobseeker jobseeker, Job job, HttpResponse response, HttpResponseBroker responseBroker)
  {
    if(jobseeker.forcedToCompleteProfile(jobseeker.getProfile()))
    {
      responseBroker.provideResponse(response, job.toMap(), "completeResumePlease");
      return response;
    }
    responseBroker.provideResponse(response, job.toMap(), "success");
    return response;
  }

  private HttpResponse provideInvalidJobResponse(HttpResponse response, String jobIdString)
  {
	int jobId = Integer.parseInt(jobIdString);
    HashMap<String, Object > model = new HashMap<String, Object>();
    model.put("jobId", jobId);
    responseBroker.provideResponse(response, model, "invalidJob");
    return response;
  }
}
