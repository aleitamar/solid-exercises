package com.theladders.solid.lsp;

import java.util.HashMap;
import java.util.Map;

public class Environment {
  private final Map<String, String> config;
  private String hostName;
  
  public Environment(String hostName)
  {
    this.config = new HashMap<>();
    this.hostName = hostName;
    buildDefaultValues();
  }
  
  public String getValue(String key)
  {
    return config.get(key);
  }
  
  public void setValue(String key, String value)
  {
    config.put(key, value);
  }
  
  public Map<String, String> toMap()
  {
    return config;
  }
  
  private void buildDefaultValues()
  {
    setValue("home", "http://" + hostName);
    setValue("secureHome", "https://" + hostName);
  }
}
