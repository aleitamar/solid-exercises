package com.theladders.solid.ocp;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.theladders.solid.ocp.jobseeker.JobseekerConfidentialityProfile;
import com.theladders.solid.ocp.jobseeker.JobseekerConfidentialityProfileDao;
import com.theladders.solid.ocp.resume.ConfidentialPhraseGroup;
import com.theladders.solid.ocp.resume.ConfidentialPhraseItem;
import com.theladders.solid.ocp.resume.ConfidentialResumeHandler;
import com.theladders.solid.ocp.resume.JobseekerProfile;
import com.theladders.solid.ocp.resume.JobseekerProfileManager;
import com.theladders.solid.ocp.resume.ResumeConfidentialityManager;
import com.theladders.solid.ocp.user.User;

public class OCPTest
{
  @Test
  public void aUserShouldBeAbleToResetConfidentialityForAllCategories()
  {
    JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager();
    JobseekerConfidentialityProfileDao jobseekerConfidentialityProfileDao = new JobseekerConfidentialityProfileDao();
    ConfidentialResumeHandler confidentialResumeHandler = new ConfidentialResumeHandler(jobseekerProfileManager, jobseekerConfidentialityProfileDao);
    ResumeConfidentialityManager resumeConfidentialityManager = new ResumeConfidentialityManager(confidentialResumeHandler);

    int id = 1;
    User user = new User(id);

    JobseekerProfile jobseekerProfile = jobseekerProfileManager.getJobSeekerProfile(user);
    JobseekerConfidentialityProfile jobseekerConfidentialityProfile = jobseekerConfidentialityProfileDao.fetchJobSeekerConfidentialityProfile(jobseekerProfile.getId());

    List<ConfidentialPhraseItem> categories = getAllPhraseCategories();

    for (ConfidentialPhraseItem category : categories)
    {
      jobseekerConfidentialityProfile.setConfidential(category);
      assertTrue(jobseekerConfidentialityProfile.isConfidential(category));
    }

    resumeConfidentialityManager.makePhraseGroupNonConfidential(jobseekerConfidentialityProfile,
                                                                ConfidentialPhraseGroup.ALL);

    for (ConfidentialPhraseItem category : categories)
    {
      assertTrue(!jobseekerConfidentialityProfile.isConfidential(category));
    }
  }

  @Test
  public void aUserShouldBeAbleToReSetConfidentialityForAllContactInfo(){
    JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager();
    JobseekerConfidentialityProfileDao jobseekerConfidentialityProfileDao = new JobseekerConfidentialityProfileDao();
    ConfidentialResumeHandler confidentialResumeHandler = new ConfidentialResumeHandler(jobseekerProfileManager, jobseekerConfidentialityProfileDao);
    ResumeConfidentialityManager resumeConfidentialityManager = new ResumeConfidentialityManager(confidentialResumeHandler);

    int id = 1;
    User user = new User(id);

    JobseekerProfile jobseekerProfile = jobseekerProfileManager.getJobSeekerProfile(user);
    JobseekerConfidentialityProfile jobseekerConfidentialityProfile = jobseekerConfidentialityProfileDao.fetchJobSeekerConfidentialityProfile(jobseekerProfile.getId());
    
    

    jobseekerConfidentialityProfile.setConfidential(ConfidentialPhraseItem.Name);
    assertTrue(jobseekerConfidentialityProfile.isConfidential(ConfidentialPhraseItem.Name));
    List<ConfidentialPhraseItem> categories = getContactInfoPhraseCategories();
    for (ConfidentialPhraseItem category : categories)
    {
      jobseekerConfidentialityProfile.setConfidential(category);
      assertTrue(jobseekerConfidentialityProfile.isConfidential(category));
    }

    resumeConfidentialityManager.makePhraseGroupNonConfidential(jobseekerConfidentialityProfile,
                                                                ConfidentialPhraseGroup.CONTACT_INFO);

    assertTrue(jobseekerConfidentialityProfile.isConfidential(ConfidentialPhraseItem.Name));
    for (ConfidentialPhraseItem category : categories)
    {
      assertTrue(!jobseekerConfidentialityProfile.isConfidential(category));
    }
  }
  
  public List<ConfidentialPhraseItem> getAllPhraseCategories()
  {
    List<ConfidentialPhraseItem> categories = new ArrayList<ConfidentialPhraseItem>();
    categories.add(ConfidentialPhraseItem.Name);
    categories.add(ConfidentialPhraseItem.MailingAddress);
    categories.add(ConfidentialPhraseItem.PhoneNumber);
    categories.add(ConfidentialPhraseItem.EmailAddress);
    categories.add(ConfidentialPhraseItem.CompanyName);
    categories.add(ConfidentialPhraseItem.WorkExperience);
    return categories;
  }

  public List<ConfidentialPhraseItem> getContactInfoPhraseCategories()
  {
    List<ConfidentialPhraseItem> categories = new ArrayList<ConfidentialPhraseItem>();
    categories.add(ConfidentialPhraseItem.MailingAddress);
    categories.add(ConfidentialPhraseItem.PhoneNumber);
    categories.add(ConfidentialPhraseItem.EmailAddress);
    return categories;
  }

}