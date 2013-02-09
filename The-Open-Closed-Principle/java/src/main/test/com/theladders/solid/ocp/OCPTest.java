package com.theladders.solid.ocp;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.theladders.solid.ocp.jobseeker.JobseekerConfidentialityProfile;
import com.theladders.solid.ocp.jobseeker.JobseekerConfidentialityProfileDao;
import com.theladders.solid.ocp.resume.ConfidentialPhraseCategory;
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

    List<ConfidentialPhraseCategory> categories = getAllPhraseCategories();

    for (ConfidentialPhraseCategory category : categories)
    {
      jobseekerConfidentialityProfile.setConfidential(category);
      assertTrue(jobseekerConfidentialityProfile.isConfidential(category));
    }

    resumeConfidentialityManager.makeAllCategoriesNonConfidential(jobseekerConfidentialityProfile);

    for (ConfidentialPhraseCategory category : categories)
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

    List<ConfidentialPhraseCategory> categories = getContactInfoPhraseCategories();

    jobseekerConfidentialityProfile.setConfidential(ConfidentialPhraseCategory.Name);
    assertTrue(jobseekerConfidentialityProfile.isConfidential(ConfidentialPhraseCategory.Name));
    for (ConfidentialPhraseCategory category : categories)
    {
      jobseekerConfidentialityProfile.setConfidential(category);
      assertTrue(jobseekerConfidentialityProfile.isConfidential(category));
    }

    resumeConfidentialityManager.makeAllContactInfoNonConfidential(jobseekerConfidentialityProfile);

    assertTrue(jobseekerConfidentialityProfile.isConfidential(ConfidentialPhraseCategory.Name));
    for (ConfidentialPhraseCategory category : categories)
    {
      assertTrue(!jobseekerConfidentialityProfile.isConfidential(category));
    }
  }

  public List<ConfidentialPhraseCategory> getAllPhraseCategories()
  {
    List<ConfidentialPhraseCategory> categories = new ArrayList<ConfidentialPhraseCategory>();
    categories.add(ConfidentialPhraseCategory.Name);
    categories.add(ConfidentialPhraseCategory.MailingAddress);
    categories.add(ConfidentialPhraseCategory.PhoneNumber);
    categories.add(ConfidentialPhraseCategory.EmailAddress);
    categories.add(ConfidentialPhraseCategory.ContactInfo);
    categories.add(ConfidentialPhraseCategory.CompanyName);
    categories.add(ConfidentialPhraseCategory.WorkExperience);
    return categories;
  }

  public List<ConfidentialPhraseCategory> getContactInfoPhraseCategories()
  {
    List<ConfidentialPhraseCategory> categories = new ArrayList<ConfidentialPhraseCategory>();
    categories.add(ConfidentialPhraseCategory.MailingAddress);
    categories.add(ConfidentialPhraseCategory.PhoneNumber);
    categories.add(ConfidentialPhraseCategory.EmailAddress);
    categories.add(ConfidentialPhraseCategory.ContactInfo);
    return categories;
  }

}