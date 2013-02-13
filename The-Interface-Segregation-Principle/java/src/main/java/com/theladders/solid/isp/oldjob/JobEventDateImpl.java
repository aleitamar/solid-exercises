package com.theladders.solid.isp.oldjob;

import java.util.Date;

public abstract class JobEventDateImpl implements JobEventDate
{
  private Date                     updateTime         = null;
  private Date                     entryDate;
  private Date                     publicationDate;
  
  public Date getEntryDate()
  {
    return entryDate;
  }
  public Date getPublicationDate()
  {
    return publicationDate;
  }
  
  public Date getUpdateTime()
  {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime)
  {
    this.updateTime = updateTime;
  }

  public void setPublicationDate(Date publicationDate)
  {
    this.publicationDate = publicationDate;
  }
  
  public void setEntryDate(Date entryDate)
  {
    this.entryDate = entryDate;
  }

}
