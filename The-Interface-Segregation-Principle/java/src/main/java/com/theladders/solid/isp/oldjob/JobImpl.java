package com.theladders.solid.isp.oldjob;

import java.util.List;

import com.theladders.solid.isp.oldjob.stubs.Discipline;
import com.theladders.solid.isp.oldjob.stubs.Experience;
import com.theladders.solid.isp.oldjob.stubs.Industry;

public abstract class JobImpl implements JobCommon
{
  private JobCompanyImpl      jobCompanyImpl;
  private JobCompensationImpl jobCompensationImpl;
  private JobEventDateImpl    jobEventDateImpl;
  private JobDecoratorImpl    jobDecoratorImpl;
  private JobSettingsImpl     jobSettingsImpl;
  private JobLocationImpl     jobLocationImpl;
  
  private List<Discipline>    disciplines;
  private Experience          experience;
  private Industry            industry;
  private int                 oldJobId     = 0;
  private String              reportsTo;
  private int                 subscriberId = 0;
  

  
  public JobSettingsImpl getJobSettingsImpl(){
    return jobSettingsImpl;
  }

  public JobLocationImpl getJobLocationImpl()
  {
    return jobLocationImpl;
  }


  public JobCompensationImpl getJobCompenationImpl()
  {
    return jobCompensationImpl;
  }


  public JobCompanyImpl getJobCompanyImpl()
  {
    return jobCompanyImpl;
  }


  public JobEventDateImpl getJobEventDateImpl()
  {
    return jobEventDateImpl;
  }


  public JobDecoratorImpl getJobDecoratorImpl()
  {
    return jobDecoratorImpl;
  }


  public List<Discipline> getDisciplines()
  {
    return disciplines;
  }


  public void setDisciplines(List<Discipline> disciplines)
  {
    this.disciplines = disciplines;
  }


  public Experience getExperience()
  {
    return experience;
  }


  public void setExperience(Experience experience)
  {
    this.experience = experience;
  }


  public Industry getIndustry()
  {
    return industry;
  }


  public void setIndustry(Industry industry)
  {
    this.industry = industry;
  }


  public int getOldJobId()
  {
    return oldJobId;
  }


  public void setOldJobId(int oldJobId)
  {
    this.oldJobId = oldJobId;
  }


  public String getReportsTo()
  {
    return reportsTo;
  }


  public void setReportsTo(String reportsTo)
  {
    this.reportsTo = reportsTo;
  }


  public int getSubscriberId()
  {
    return subscriberId;
  }


  public void setSubscriberId(Integer subscriberId)
  {
    this.subscriberId = subscriberId;
  }


}
