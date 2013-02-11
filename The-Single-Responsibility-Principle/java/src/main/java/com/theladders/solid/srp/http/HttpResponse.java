package com.theladders.solid.srp.http;

import com.theladders.solid.srp.Result;

// responsibilities
// - represents an http response that has a result

public class HttpResponse
{
  private Result result;


  public String getResultType()
  {
    return result.getType();
  }


  public void setResult(Result result)
  {
    this.result = result;
  }
}
