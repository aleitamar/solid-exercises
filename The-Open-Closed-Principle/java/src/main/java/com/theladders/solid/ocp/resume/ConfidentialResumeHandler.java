package com.theladders.solid.ocp.resume;

import com.theladders.solid.ocp.jobseeker.JobseekerConfidentialityProfile;
import com.theladders.solid.ocp.jobseeker.JobseekerConfidentialityProfileDao;
import com.theladders.solid.ocp.user.User;

public class ConfidentialResumeHandler
{
  private final JobseekerProfileManager            jobSeekerProfileManager;
  private final JobseekerConfidentialityProfileDao jobseekerConfidentialityProfileDao;

  public ConfidentialResumeHandler(JobseekerProfileManager jobseekerProfileManager,
                                   JobseekerConfidentialityProfileDao jobseekerConfidentialityProfileDao)
  {
    this.jobSeekerProfileManager = jobseekerProfileManager;
    this.jobseekerConfidentialityProfileDao = jobseekerConfidentialityProfileDao;
  }

  public void makePhraseGroupNonConfidential(JobseekerConfidentialityProfile jobseekerConfidentialityProfile,
                                             ConfidentialPhraseGroup confidentialPhraseGroup)
  {
    boolean isChanged = jobseekerConfidentialityProfile.resetConfidentialFlagsForCategoryGroup(confidentialPhraseGroup);
    if (isChanged) { doSomething(); }
  }

  public boolean isConfidential(User user, ConfidentialPhraseItem confidentialPhraseItem){
    JobseekerProfile jobseekerProfile = jobSeekerProfileManager.getJobSeekerProfile(user);
    JobseekerConfidentialityProfile jobseekerConfidentialityProfile = jobseekerConfidentialityProfileDao.fetchJobSeekerConfidentialityProfile(jobseekerProfile.getId());
    
    return jobseekerConfidentialityProfile.isConfidential(confidentialPhraseItem);
    
  }
  
  private void doSomething() {}
}
