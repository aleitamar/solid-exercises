package com.theladders.solid.isp.oldjob;

import com.theladders.solid.isp.oldjob.stubs.Region;

public interface JobLocation
{
  /**
   * Get the region for this job.
   * 
   * @return the region for this job.
   */
  Region getRegion();


  String getLocation();
}
