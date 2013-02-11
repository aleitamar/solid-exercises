package com.theladders.solid.srp.job;

import java.util.HashMap;
import java.util.Map;

// responsibilities
// - represents a decorated version of a job for display

public class JobDecorator
{
  public final Job model;


  public JobDecorator(Job job)
  {
    this.model = job;
  }


  public Map<String, Object> toMap()
  {
    Map<String, Object> map = new HashMap<>();
    map.put("jobId", model.getJobId());
    map.put("jobTitle", getTitle());
    return map;
  }


  public String getTitle()
  {
    return "This is a job with id:" + Integer.toString(model.getJobId());
  }
}
