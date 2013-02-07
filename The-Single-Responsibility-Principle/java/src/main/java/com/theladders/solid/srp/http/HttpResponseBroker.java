package com.theladders.solid.srp.http;

import java.util.Map;
import java.util.List;

import com.theladders.solid.srp.ErrorResult;
import com.theladders.solid.srp.Result;

// responsibilities
// - builds different types of results and passes them to the http response

public class HttpResponseBroker {  
  public void provideResponse(HttpResponse response, Map<String, Object> model, String response_text)
  {
    Result result = new Result(response_text, model);
    setResult(response, result);
  }

  public void provideResponseWithList(HttpResponse response, Map<String, Object> model, String response_text, List<String> list)
  {
    Result result = new ErrorResult(response_text, model, list);
    setResult(response, result);
  }

  private static void setResult(HttpResponse response, Result result){
    response.setResult(result);
  }
}
