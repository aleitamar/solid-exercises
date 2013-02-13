package com.theladders.solid.isp.oldjob;

import com.theladders.solid.isp.oldjob.stubs.Region;

public abstract class JobLocationImpl implements JobLocation
{
  private String                   location;
  private Region                   region;
  
  public String getLocation()
  {
    return location;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }
  
  public void setRegion(Region region)
  {
    this.region = region;
  }

  public Region getRegion()
  {
    return region;
  }
}
