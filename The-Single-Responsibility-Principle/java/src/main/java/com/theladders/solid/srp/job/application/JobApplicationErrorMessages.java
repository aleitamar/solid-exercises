package com.theladders.solid.srp.job.application;

import java.util.ArrayList;
import java.util.List;

public class JobApplicationErrorMessages
{
  private final List<String> errorMessageList;


  public JobApplicationErrorMessages()
  {
    this.errorMessageList = new ArrayList<>();
  }


  public List<String> getGenericErrorList()
  {
    errorMessageList.add("We could not process your application");
    return errorMessageList;
  }
}
