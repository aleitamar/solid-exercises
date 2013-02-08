package com.theladders.solid.srp.job.application;


public class JobApplicationSystem
{
  private final JobApplicationRepository repository;

  public JobApplicationSystem(JobApplicationRepository repository)
  {
    this.repository = repository;
  }

  public JobApplicationResult apply(UnprocessedApplication application)
  {
    if (application.isValid())
    {
      SuccessfulApplication successfulApplication = new SuccessfulApplication(application.getJobseeker(),
                                                                              application.getJob(),
                                                                              application.getResume());
      if(repository.add(successfulApplication)) { return successfulApplication; }
    }

    return new FailedApplication();
  }
}
