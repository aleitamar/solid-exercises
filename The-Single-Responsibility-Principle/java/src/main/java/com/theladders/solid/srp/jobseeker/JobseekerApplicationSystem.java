package com.theladders.solid.srp.jobseeker;

import java.util.Map;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.resume.Resume;

public class JobseekerApplicationSystem
{
  private final Jobseeker jobseeker;


  public JobseekerApplicationSystem(Jobseeker jobseeker)
  {
    this.jobseeker = jobseeker;
  }


  public void applyWithResume(Job job,
                              JobApplicationSystem jobApplicationSystem,
                              ResumeManager resumeManager)
  {
    Resume resume = resumeManager.getActiveResume(jobseeker);
    JobApplicationResult jobApplicationResult = apply(resume, job, jobApplicationSystem);
    if (jobApplicationResult.failure())
    {
      raiseApplicationFailureException(jobApplicationResult);
    }
  }


  public void applyWithFile(Job job,
                            JobApplicationSystem jobApplicationSystem,
                            ResumeManager resumeManager,
                            Map<String, Boolean> resumeOptions,
                            String fileName)
  {
    Resume resume = resumeManager.createResumeWithOptions(jobseeker, resumeOptions, fileName);
    JobApplicationResult jobApplicationResult = apply(resume, job, jobApplicationSystem);
    if (jobApplicationResult.failure())
    {
      resume.destroy();
      raiseApplicationFailureException(jobApplicationResult);
    }
  }


  private JobApplicationResult apply(Resume resume,
                                     Job job,
                                     JobApplicationSystem jobApplicationSystem)
  {
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    JobApplicationResult applicationResult = jobApplicationSystem.apply(application);
    return applicationResult;
  }


  private void raiseApplicationFailureException(JobApplicationResult jobApplicationResult)
  {
    throw new ApplicationFailureException(jobApplicationResult.toString());
  }
}
