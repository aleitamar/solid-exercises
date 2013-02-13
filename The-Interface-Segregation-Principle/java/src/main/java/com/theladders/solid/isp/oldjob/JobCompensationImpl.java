package com.theladders.solid.isp.oldjob;

public abstract class JobCompensationImpl implements JobCompensation
{
  private String                   compensation;
  private String                   compensationBonus;
  private String                   compensationOther;
  private String                   compensationSalary;
  
  public String getCompensation()
  {
    return compensation;
  }

  public void setCompensation(String compensation)
  {
    this.compensation = compensation;
  }

  public String getCompensationBonus()
  {
    return compensationBonus;
  }

  public void setCompensationBonus(String compensationBonus)
  {
    this.compensationBonus = compensationBonus;
  }

  public String getCompensationOther()
  {
    return compensationOther;
  }

  public void setCompensationOther(String compensationOther)
  {
    this.compensationOther = compensationOther;
  }

  public String getCompensationSalary()
  {
    return compensationSalary;
  }

  public void setCompensationSalary(String compensationSalary)
  {
    this.compensationSalary = compensationSalary;
  }
}
