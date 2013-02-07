package com.theladders.solid.srp;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.http.ResponseStatus;

// responsibilities
// - represents a result returned to the user when a controller returns

public class Result
{
  private final ResponseStatus responseStatus;
  private final Map<String, Object> model;

  public Result(ResponseStatus responseStatus, Map<String, Object> model)
  {
    this.responseStatus  = responseStatus;
    this.model = model;
  }

  public String getType()
  {
    return responseStatus.getStatusString();
  }

  public Map<String, Object> getModel()
  {
    return model;
  }

  public Map<String, Object> toMap()
  {
    Map<String, Object> map = new HashMap<>();
    map.put("type", getType());
    map.put("type", model);
    return map;
  }
}
