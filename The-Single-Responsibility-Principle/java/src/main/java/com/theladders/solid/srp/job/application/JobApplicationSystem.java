package com.theladders.solid.srp.job.application;


public class JobApplicationSystem
{
  private final JobApplicationRepository repository;
  private final String errorMessage;

  public JobApplicationSystem(JobApplicationRepository repository)
  {
    this.repository = repository;
    this.errorMessage = "We could not process your application.";
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

  public String getJobApplicationFailedMessage()
  {
    return errorMessage;  
  }
}
