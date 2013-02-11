package com.theladders.solid.srp;


import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.job.JobDecorator;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerApplicationSystem;
import com.theladders.solid.srp.resume.MyResumeManager;

public class ApplyWithResumeController
{
  private final JobSearchService        jobSearchService;
  private final JobApplicationSystem    jobApplicationSystem;
  private final MyResumeManager         myResumeManager;
  private final ApplyResponder          applyResponder;

  public ApplyWithResumeController(JobSearchService jobSearchService,
                         JobApplicationSystem jobApplicationSystem,
                         MyResumeManager myResumeManager)
  {
    this.jobSearchService     = jobSearchService;
    this.jobApplicationSystem = jobApplicationSystem;
    this.myResumeManager      = myResumeManager;
    this.applyResponder       = new ApplyResponder();
  }

  public HttpResponse handle(HttpRequest request, HttpResponse response)
  {
    ApplyWithFileParams applyWithFileParams   = new ApplyWithFileParams(request, jobSearchService);
    Jobseeker jobseeker       = applyWithFileParams.getJobseeker();
    JobDecorator jobDecorator = new JobDecorator(applyWithFileParams.getJob());    
    
    if (jobDecorator.model == null) { return applyResponder.provideInvalidJobResponse(response, applyWithFileParams.getJobId()); }
    
    try { 
      JobseekerApplicationSystem jobseekerApplicationSystem = new JobseekerApplicationSystem(jobseeker);
      jobseekerApplicationSystem.applyWithResume(jobDecorator.model, jobApplicationSystem, myResumeManager);
    }
    catch (Exception e) { return applyResponder.respondOnApplicationError(response, jobDecorator); }

    return applyResponder.respondOnApplicationSuccess(jobseeker, jobDecorator, response);
  }
}
