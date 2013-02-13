package com.theladders.solid.isp.oldjob;

public abstract class JobSettingsImpl implements JobSettings
{
  private boolean             anonymous    = false;
  private boolean             confidential = false;
  private boolean             exclusive    = false;
  private boolean             reimbursable = false;
  
  public boolean isAnonymous()
  {
    return anonymous;
  }

  public void setAnonymous(boolean anonymous)
  {
    this.anonymous = anonymous;
  }


  public boolean isConfidential()
  {
    return confidential;
  }


  public void setConfidential(boolean confidential)
  {
    this.confidential = confidential;
  }


  public boolean isExclusive()
  {
    return exclusive;
  }


  public void setExclusive(boolean exclusive)
  {
    this.exclusive = exclusive;
  }


  public boolean isReimbursable()
  {
    return reimbursable;
  }


  public void setReimbursable(boolean reimbursable)
  {
    this.reimbursable = reimbursable;
  }

}
