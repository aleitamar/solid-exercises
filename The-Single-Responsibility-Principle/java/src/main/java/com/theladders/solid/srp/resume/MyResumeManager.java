package com.theladders.solid.srp.resume;

import java.util.Map;

import com.theladders.solid.srp.jobseeker.Jobseeker;

public class MyResumeManager
{
  private final ActiveResumeRepository repository;
  private final ResumeManager          resumeManager;

  public MyResumeManager(ActiveResumeRepository repository, ResumeManager resumeManager)
  {
    this.repository = repository;
    this.resumeManager = resumeManager;
  }

  public Resume createResumeWithOptions(Jobseeker jobseeker, Map<String, Boolean> options, String filename)
  {  
    Resume resume = resumeManager.saveResume(jobseeker, filename);
    if (resume != null && options.get("shouldMakeResumeActive"))
    {
      saveAsActive(jobseeker, resume);
    }
    return resume;
  }

  public void saveAsActive(Jobseeker jobseeker,
                           Resume resume)
  {
    repository.makeActive(jobseeker.getId(), resume);
  }

  public Resume getActiveResume(Jobseeker jobseeker)
  {
    return repository.activeResumeFor(jobseeker.getId());
  }

}