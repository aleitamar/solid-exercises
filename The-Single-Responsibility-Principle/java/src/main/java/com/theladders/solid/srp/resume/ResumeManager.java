package com.theladders.solid.srp.resume;

import java.util.Map;

import com.theladders.solid.srp.jobseeker.Jobseeker;

public class ResumeManager
{
  private final ActiveResumeRepository activeResumeRepository;
  private final ResumeRepository       resumeRepository;


  public ResumeManager(ActiveResumeRepository activeResumeRepository,
                       ResumeRepository resumeRepository)
  {
    this.activeResumeRepository = activeResumeRepository;
    this.resumeRepository = resumeRepository;
  }


  public Resume createResumeWithOptions(Jobseeker jobseeker,
                                        Map<String, Boolean> options,
                                        String filename)
  {
    Resume resume = saveResume(jobseeker, filename);
    if (resume != null && options.get("shouldMakeResumeActive"))
    {
      makeResumeActive(jobseeker, resume);
    }
    return resume;
  }


  public Resume getActiveResume(Jobseeker jobseeker)
  {
    return activeResumeRepository.activeResumeFor(jobseeker.getId());
  }


  private Resume saveResume(Jobseeker jobseeker,
                            String fileName)
  {
    Resume resume = new Resume(fileName);
    resumeRepository.saveResume(jobseeker.getId(), resume);
    return resume;
  }


  private void makeResumeActive(Jobseeker jobseeker,
                                Resume resume)
  {
    activeResumeRepository.makeActive(jobseeker.getId(), resume);
  }
}
