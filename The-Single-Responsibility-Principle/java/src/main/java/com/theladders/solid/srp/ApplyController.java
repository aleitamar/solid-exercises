package com.theladders.solid.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.http.HttpResponseBroker;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.jobseeker.ProfileStatus;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.resume.ResumeSearchService;

public class ApplyController
{
  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobSearchService        jobSearchService;
  private final ResumeSearchService     resumeSearchService;
  private final JobApplicationSystem    jobApplicationSystem;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;
  private final HttpResponseBroker          responseBroker;
  
  //Constructor
  public ApplyController(JobseekerProfileManager jobseekerProfileManager,
		                 ResumeSearchService resumeSearchService,
                         JobSearchService jobSearchService,
                         JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager, //huh?
                         MyResumeManager myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobSearchService = jobSearchService;
    this.resumeSearchService = resumeSearchService;
    this.jobApplicationSystem = jobApplicationSystem;
    this.resumeManager = resumeManager;
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
    responseBroker.provideResponseWithList(response, job.getReponsePayload(), "error", errList);
    return response;
  }
  
  private HttpResponse respondOnApplicationSuccess(Jobseeker jobseeker, Job job, HttpResponse response, HttpResponseBroker responseBroker)
  {
    if(jobseeker.forcedToCompleteProfile(jobseeker.profile))
    {
      responseBroker.provideResponse(response, job.getReponsePayload(), "completeResumePlease");
      return response;
    }

    responseBroker.provideResponse(response, job.getReponsePayload(), "success");
    return response;
  }

  private HttpResponse provideInvalidJobResponse(HttpResponse response, final String jobIdString)
  {
	final int jobId = Integer.parseInt(jobIdString);
    HashMap<String, Object > model = new HashMap<String, Object>(){{ put("jobId", jobId); }};
    responseBroker.provideResponse(response, model, "invalidJob");
    return response;
  }
}
