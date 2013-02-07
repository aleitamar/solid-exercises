package com.theladders.solid.srp;

import java.util.HashMap;
import java.util.Map;

// responsibilities
// - represents a result returned to the user when a controller returns

public class Result
{
  private final String type;
  private final Map<String, Object> model;

  public Result(String type,
                Map<String, Object> model)
  {
    this.type  = type;
    this.model = model;
  }

  public String getType()
  {
    return type;
  }

  public Map<String, Object> getModel()
  {
    return model;
  }

  public Map<String, Object> toMap()
  {
    Map<String, Object> map = new HashMap<>();
    map.put("type", type);
    map.put("type", model);
    return map;
  }
}
