package com.theladders.solid.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.ResponseBroker;
import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
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

public class ApplyController
{
  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobSearchService        jobSearchService;
  private final JobApplicationSystem    jobApplicationSystem;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;
  private final ResponseBroker          responseBroker;
  
  //Constructor
  public ApplyController(JobseekerProfileManager jobseekerProfileManager,
                         JobSearchService jobSearchService,
                         JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager, //huh?
                         MyResumeManager myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobSearchService = jobSearchService;
    this.jobApplicationSystem = jobApplicationSystem;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
    this.responseBroker = new ResponseBroker();
  }

  public HttpResponse handle(HttpRequest request, HttpResponse response, String origFileName)
  {
    Jobseeker jobseeker      = request.getSession().getJobseeker();
    JobseekerProfile profile = jobseekerProfileManager.getJobSeekerProfile(jobseeker);
    Job job                  = jobSearchService.getJobByIdString(request.getParameter("jobId"));
    
    //when job is null, return 404 or some such
    if (job == null)
    {
      provideInvalidJobResponse(response, request.getParameter("jobId"));
      return response;
    }

    Map<String, Object> model = job.getReponsePayload();
    
    try
    {
      apply(request, jobseeker, job, origFileName);
    }
    catch (Exception e)
    {
      List<String> errList = new ArrayList<>();
      errList.add("We could not process your application.");
      responseBroker.provideResponseWithList(response, model, "error", errList);
      return response;
    }
    
    if(jobseeker.forcedToCompleteProfile(profile))
    {
      responseBroker.provideResponse(response, model, "completeResumePlease");
      return response;
    }

    // render a success view
    responseBroker.provideResponse(response, model, "success");

    return response;
  }

  //applying for a job
  private void apply(HttpRequest request,
                     Jobseeker jobseeker,
                     Job job,
                     String fileName)
  {
	//find or create resume for jobseeker
    Resume resume = saveNewOrRetrieveExistingResume(fileName,jobseeker, request);
    //create an unprocessed application
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    //calling apply to the job application system
    JobApplicationResult applicationResult = jobApplicationSystem.apply(application);

    //if the application fails, throw an error
    if (applicationResult.failure())
    {
      throw new ApplicationFailureException(applicationResult.toString());
    }
  }

  //find or create resume by jobseeker
  private Resume saveNewOrRetrieveExistingResume(String newResumeFileName,
                                                 Jobseeker jobseeker,
                                                 HttpRequest request)
  {
	//initialize resume
    Resume resume;

    // if it doesn't already exists
    if (!"existing".equals(request.getParameter("whichResume")))
    {
      // creates a resume with the given filename
      resume = resumeManager.saveResume(jobseeker, newResumeFileName);

      //if the resume is not null and the user chooses to make the resume active
      if (resume != null && "yes".equals(request.getParameter("makeResumeActive")))
      {
    	// save resume
        myResumeManager.saveAsActive(jobseeker, resume);
      }
    }
    //get existing resume
    else
    {
      resume = myResumeManager.getActiveResume(jobseeker.getId());
    }

    return resume;
  }

  //inconsistent param types
  private void provideInvalidJobResponse(HttpResponse response, final String jobIdString)
  {
	final int jobId = Integer.parseInt(jobIdString);
    HashMap<String, Object > model = new HashMap<String, Object>(){{ put("jobId", jobId); }};
    responseBroker.provideResponse(response, model, "invalidJob");
  }
}
