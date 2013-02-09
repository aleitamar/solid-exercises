package com.theladders.solid.ocp.resume;

import java.util.List;

public class ResumeDetailCategory extends ResumeSensitiveInformation
{
  private List<ResumeDetail> resumeDetails;

  public ResumeDetailCategory(List<ResumeDetail> resumeDetails)
  {
    this.resumeDetails = resumeDetails;
  }
  
  public List<ResumeDetail> getResumeDetails()
  {
    return resumeDetails;
  }
  
  public void setConfidential()
  {
    for(ResumeDetail resumeDetail : resumeDetails) { resumeDetail.setConfidential(); }
    super.setConfidential();
  }
  
  public void setPublic()
  {
    for(ResumeDetail resumeDetail : resumeDetails) { resumeDetail.setPublic(); }
    super.setPublic();
  }
}
