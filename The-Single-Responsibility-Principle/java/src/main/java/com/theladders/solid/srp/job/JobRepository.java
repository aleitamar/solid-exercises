package com.theladders.solid.srp.job;

import java.util.HashMap;
import java.util.Map;

// responsibilities
// - persist jobs (add, retrieve)

public class JobRepository
{
  private final Map<Integer, Job> jobs;

  public JobRepository()
  {
    this.jobs = new HashMap<>();
  }

  public void addJob(Job aJob)
  {
    jobs.put(aJob.getJobId(), aJob);
  }

  public Job getJob(int jobId)
  {
    return jobs.get(jobId);
  }
}
