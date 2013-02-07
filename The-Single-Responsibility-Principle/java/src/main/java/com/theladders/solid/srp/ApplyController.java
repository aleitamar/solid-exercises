package com.theladders.solid.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.http.HttpResponseBroker;
import com.theladders.solid.srp.job.JobDecorator;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;

public class ApplyController
{
  private final JobSearchService        jobSearchService;
  private final JobApplicationSystem    jobApplicationSystem;
  private final MyResumeManager         myResumeManager;
  private final HttpResponseBroker      responseBroker;

  //Constructor
  public ApplyController(JobSearchService jobSearchService,
                         JobApplicationSystem jobApplicationSystem,
                         MyResumeManager myResumeManager)
  {
    this.jobSearchService     = jobSearchService;
    this.jobApplicationSystem = jobApplicationSystem;
    this.myResumeManager      = myResumeManager;
    this.responseBroker       = new HttpResponseBroker();
  }

  public HttpResponse handle(HttpRequest request, HttpResponse response, String origFileName)
  {
    ApplyParams applyParams   = new ApplyParams(request, jobSearchService);
    Jobseeker jobseeker       = applyParams.getJobseeker();
    JobDecorator jobDecorator = new JobDecorator(applyParams.getJob());    
    if (jobDecorator.model == null)
    {
      return provideInvalidJobResponse(response, applyParams.getJobId());
    }
    try { 
      jobseeker.apply(jobDecorator.model, origFileName, applyParams.resumeOptions(), jobApplicationSystem, myResumeManager);
    }
    catch (Exception e) { return respondOnApplicationError(response, jobDecorator); }

    return respondOnApplicationSuccess(jobseeker, jobDecorator, response);
  }

  private HttpResponse respondOnApplicationError(HttpResponse response, JobDecorator jobDecorator)
  {
    List<String> errList = new ArrayList<>();
    errList.add(jobApplicationSystem.getJobApplicationFailedMessage());
    responseBroker.provideResponseWithList(response, jobDecorator.toMap(), "error", errList);
    return response;
  }

  private HttpResponse respondOnApplicationSuccess(Jobseeker jobseeker, JobDecorator jobDecorator, HttpResponse response)
  {
    if(jobseeker.forcedToCompleteProfile())
    {
      responseBroker.provideResponse(response, jobDecorator.toMap(), "completeResumePlease");
      return response;
    }
    responseBroker.provideResponse(response, jobDecorator.toMap(), "success");
    return response;
  }

  private HttpResponse provideInvalidJobResponse(HttpResponse response, String jobIdString)
  {
    int jobId = Integer.parseInt(jobIdString);
    HashMap<String, Object > model = new HashMap<>();
    model.put("jobId", jobId);
    responseBroker.provideResponse(response, model, "invalidJob");
    return response;
  }
}
