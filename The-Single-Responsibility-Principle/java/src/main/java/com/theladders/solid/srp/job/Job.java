package com.theladders.solid.srp.job;

// responsibilities
// - represents a job and allows access to its attributes

public class Job
{
  private final int id;


  public Job(int id)
  {
    this.id = id;
  }


  public int getJobId()
  {
    return id;
  }


  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    return result;
  }


  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    Job other = (Job) obj;
    if (id != other.id)
      return false;
    return true;
  }
}
