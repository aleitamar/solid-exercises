package com.theladders.solid.srp.http;

public enum ResponseStatus
{

  SUCCESS("success"),
  COMPLETE_RESUME("completeResumePlease"),
  ERROR("error"),
  INVALID_JOB("invalidJob");

  private final String status;


  ResponseStatus(String status)
  {
    this.status = status;
  }


  public String getStatusString()
  {
    return status;
  }

}
