package com.theladders.solid.isp.oldjob;

public abstract class JobCompanyImpl implements JobCompany
{
  private String                   company;
  private Integer                  companySize;
  
  public String getCompany()
  {
    return company;
  }

  public void setCompany(String company)
  {
    this.company = company;
  }

  public Integer getCompanySize()
  {
    return companySize;
  }

  public void setCompanySize(Integer companySize)
  {
    this.companySize = companySize;
  }
}
