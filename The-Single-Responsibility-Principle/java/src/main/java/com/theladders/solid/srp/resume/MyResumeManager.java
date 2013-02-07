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
  
  public Resume findOrCreateResumeWithOptions(Jobseeker jobseeker, Map<String, Boolean> options, String filename)
  {  
    Resume resume;
	if (options.get("isNewResume"))
	{
	  resume = resumeManager.saveResume(jobseeker, filename);
	  if (resume != null && options.get("shouldMakeResumeActive"))
	  {
	    saveAsActive(jobseeker, resume);
	  }
	}
	else
	{
	  resume = getActiveResume(jobseeker.getId());
	}
	return resume;
  }

  public void saveAsActive(Jobseeker jobseeker,
                           Resume resume)
  {
    repository.makeActive(jobseeker.getId(), resume);
  }

  public Resume getActiveResume(int jobseekerId)
  {
    return repository.activeResumeFor(jobseekerId);
  }
}
