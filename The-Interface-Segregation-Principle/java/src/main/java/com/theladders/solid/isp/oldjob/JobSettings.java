package com.theladders.solid.isp.oldjob;

public interface JobSettings
{
  /**
   * @return returns true if this job was posted anonymously
   */
  boolean isAnonymous();

  boolean isConfidential();

  boolean isExclusive();

  /**
   * Is this job a JobReq? JobReqs are jobs entered into our site directly by recruiters.
   *
   * @return true if job is a JobReq, false otherwise.
   */
  boolean isJobReq();

  boolean isReimbursable();
}
