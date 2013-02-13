package com.theladders.solid.isp.oldjob;

public abstract class JobDecoratorImpl implements JobDecorator
{
  private String                   title;
  private String                   description;

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

}
