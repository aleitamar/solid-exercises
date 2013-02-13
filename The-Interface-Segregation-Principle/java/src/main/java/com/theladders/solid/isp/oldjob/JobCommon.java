package com.theladders.solid.isp.oldjob;

import java.util.List;

import com.theladders.solid.isp.oldjob.stubs.Discipline;
import com.theladders.solid.isp.oldjob.stubs.Experience;
import com.theladders.solid.isp.oldjob.stubs.Industry;

public interface JobCommon
{
  /**
   * Retrieves a list of disciplines for this job.
   *
   * @return List of Disciplines
   */
  List<Discipline> getDisciplines();

  /**
   * Returns an object that represents the number of years of experience
   * that are required for this job.
   * @return experience object
   */
  Experience getExperience();

  /**
   * Get the Industry for this job.
   *
   * @return the Industry for this job.
   */
  Industry getIndustry();

  int getOldJobId();


  /**
   * Get the "reportsTo" field.
   *
   * @return reportsTo
   */
  String getReportsTo();

  int getSubscriberId();

}
