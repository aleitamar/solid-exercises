package com.theladders.solid.srp;

import java.util.HashMap;

import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.http.HttpResponseBroker;
import com.theladders.solid.srp.http.ResponseStatus;
import com.theladders.solid.srp.job.JobDecorator;
import com.theladders.solid.srp.job.application.JobApplicationErrorMessages;
import com.theladders.solid.srp.jobseeker.Jobseeker;

public class ApplyResponder
{
  private final HttpResponseBroker responseBroker;


  public ApplyResponder()
  {
    this.responseBroker = new HttpResponseBroker();
  }


  public HttpResponse respondOnApplicationError(HttpResponse response,
                                                JobDecorator jobDecorator)
  {
    JobApplicationErrorMessages applicationErrorMessages = new JobApplicationErrorMessages();
    responseBroker.provideResponseWithList(response,
                                           jobDecorator.toMap(),
                                           ResponseStatus.ERROR,
                                           applicationErrorMessages.getGenericErrorList());
    return response;
  }


  public HttpResponse respondOnApplicationSuccess(Jobseeker jobseeker,
                                                  JobDecorator jobDecorator,
                                                  HttpResponse response)
  {
    if (jobseeker.forcedToCompleteProfile())
    {
      responseBroker.provideResponse(response, jobDecorator.toMap(), ResponseStatus.COMPLETE_RESUME);
      return response;
    }
    responseBroker.provideResponse(response, jobDecorator.toMap(), ResponseStatus.SUCCESS);
    return response;
  }


  public HttpResponse provideInvalidJobResponse(HttpResponse response,
                                                String jobIdString)
  {
    int jobId = Integer.parseInt(jobIdString);
    HashMap<String, Object> model = new HashMap<>();
    model.put("jobId", jobId);
    responseBroker.provideResponse(response, model, ResponseStatus.INVALID_JOB);
    return response;
  }

}
