package com.theladders.solid.srp.http;

import java.util.Map;
import java.util.List;

import com.theladders.solid.srp.Result;

public class HttpResponseBroker {  
  public void provideResponse(HttpResponse response, Map<String, Object> model, String response_text)
  {
    Result result = new Result(response_text, model);
    setResult(response, result);
  }
  
  public void provideResponseWithList(HttpResponse response, Map<String, Object> model, String response_text, List<String> list)
  {
    Result result = new Result(response_text, model, list);
	setResult(response, result);
  }
  
  private void setResult(HttpResponse response, Result result){
    response.setResult(result);
  }
}
