package com.theladders.solid.srp;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.http.HttpSession;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobRepository;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationRepository;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.SuccessfulApplication;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.jobseeker.JobseekerProfileRepository;
import com.theladders.solid.srp.jobseeker.ProfileStatus;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.ActiveResumeRepository;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.resume.ResumeRepository;
import com.theladders.solid.srp.resume.ResumeSearchService;

public class TestIt
{
  private static final int INVALID_JOB_ID        = 555;
  private static final String SHARED_RESUME_NAME = "A Resume";
  private static final int JOBSEEKER_WITH_RESUME = 777;
  private static final int INCOMPLETE_JOBSEEKER  = 888;
  private static final int APPROVED_JOBSEEKER    = 1010;

  private ApplyController            controller;
  private JobRepository              jobRepository;
  private ResumeRepository           resumeRepository;
  private JobApplicationRepository   jobApplicationRepository;
  private JobseekerProfileRepository jobseekerProfileRepository;
  private ActiveResumeRepository     activeResumeRepository;

  private SuccessfulApplication      existingApplication;

  @Test
  public void requestWithValidJob()
  {
	JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true, jobseekerProfileManager);
    HttpSession session = new HttpSession(JOBSEEKER);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","5");

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    controller.handle(request, response, SHARED_RESUME_NAME);

    assertEquals("success", response.getResultType());
  }

  @Test
  public void requestWithValidJobByBasic()
  {
    JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, false, jobseekerProfileManager);
    HttpSession session = new HttpSession(JOBSEEKER);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","5");

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    controller.handle(request, response, SHARED_RESUME_NAME);

    assertEquals("success", response.getResultType());
  }

  @Test
  public void applyUsingExistingResume()
  {
	JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    Jobseeker JOBSEEKER = new Jobseeker(JOBSEEKER_WITH_RESUME, true, jobseekerProfileManager);
    HttpSession session = new HttpSession(JOBSEEKER);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","5");
    parameters.put("whichResume", "existing");

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    controller.handle(request, response, SHARED_RESUME_NAME);

    assertEquals("success", response.getResultType());
  }

  @Test
  public void requestWithInvalidJob()
  {
	JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true, jobseekerProfileManager);
    HttpSession session = new HttpSession(JOBSEEKER);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId", String.valueOf(INVALID_JOB_ID));

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    controller.handle(request, response, SHARED_RESUME_NAME);

    assertEquals("invalidJob", response.getResultType());
  }

  @Test
  public void requestWithNoResume()
  {
	JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true, jobseekerProfileManager);
    HttpSession session = new HttpSession(JOBSEEKER);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId", "5");

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    controller.handle(request, response, null);

    assertEquals("error", response.getResultType());
  }

  @Test
  public void reapplyToJob()
  {
	JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true, jobseekerProfileManager);
    HttpSession session = new HttpSession(JOBSEEKER);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","15");

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    controller.handle(request, response, SHARED_RESUME_NAME);

    assertEquals("error", response.getResultType());
  }

  @Test
  public void unapprovedBasic()
  {
	JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    Jobseeker JOBSEEKER = new Jobseeker(INCOMPLETE_JOBSEEKER, false, jobseekerProfileManager);
    HttpSession session = new HttpSession(JOBSEEKER);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","5");

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    controller.handle(request, response, SHARED_RESUME_NAME);

    assertEquals("completeResumePlease", response.getResultType());
  }

  @Test
  public void resumeIsSaved()
  {
	JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true, jobseekerProfileManager);
    HttpSession session = new HttpSession(JOBSEEKER);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","5");

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    controller.handle(request, response, SHARED_RESUME_NAME);

    assertTrue(resumeRepository.contains(new Resume(SHARED_RESUME_NAME)));
  }

  @Test
  public void resumeIsMadeActive()
  {
	JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true, jobseekerProfileManager);
    HttpSession session = new HttpSession(JOBSEEKER);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","5");
    parameters.put("makeResumeActive", "yes");

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    controller.handle(request, response, "Save Me Seymour");

    assertEquals(new Resume("Save Me Seymour"), activeResumeRepository.activeResumeFor(APPROVED_JOBSEEKER));
  }

  @Before
  public void setup()
  {
    setupJobseekerProfileRepository();
    setupJobRepository();
    setupResumeRepository();
    setupActiveResumeRepository();
    setupJobApplicationRepository();
    setupController();
  }

  private void setupJobseekerProfileRepository()
  {
    jobseekerProfileRepository = new JobseekerProfileRepository();

    addToJobseekerProfileRepository(APPROVED_JOBSEEKER, ProfileStatus.APPROVED);
    addToJobseekerProfileRepository(INCOMPLETE_JOBSEEKER, ProfileStatus.INCOMPLETE);
    addToJobseekerProfileRepository(JOBSEEKER_WITH_RESUME, ProfileStatus.APPROVED);
  }

  private void addToJobseekerProfileRepository(int id, ProfileStatus status)
  {
    JobseekerProfile profile = new JobseekerProfile(id, status);
    jobseekerProfileRepository.addProfile(profile);
  }

  private void setupJobRepository()
  {
    jobRepository = new JobRepository();

    addJobToRepository(5);
    addJobToRepository(15);
    addJobToRepository(51);
    addJobToRepository(57);
    addJobToRepository(501);
    addJobToRepository(1555);
    addJobToRepository(5012);
    addJobToRepository(50111);
  }

  private void addJobToRepository(int jobId)
  {
    if (jobId != INVALID_JOB_ID)
    {
      jobRepository.addJob(new Job(jobId));
    }
  }

  private void setupResumeRepository()
  {
    resumeRepository = new ResumeRepository();
  }

  private void setupActiveResumeRepository()
  {
    activeResumeRepository = new ActiveResumeRepository();

    activeResumeRepository.makeActive(JOBSEEKER_WITH_RESUME, new Resume("Blammo"));
  }

  private void setupJobApplicationRepository()
  {
    jobApplicationRepository = new JobApplicationRepository();

    addToJobApplicationRepository();
  }

  private void addToJobApplicationRepository()
  {
	JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true, jobseekerProfileManager);
    Job job = new Job(15);
    Resume resume = new Resume("foo");

    existingApplication = new SuccessfulApplication(JOBSEEKER, job, resume);

    jobApplicationRepository.add(existingApplication);
  }

  private void setupController()
  {
    JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    JobSearchService jobSearchService = new JobSearchService(jobRepository);
    ResumeSearchService resumeSearchService = new ResumeSearchService(resumeRepository);
    JobApplicationSystem jobApplicationSystem = new JobApplicationSystem(jobApplicationRepository);
    ResumeManager resumeManager = new ResumeManager(resumeRepository);
    MyResumeManager myResumeManager = new MyResumeManager(activeResumeRepository, resumeManager);

    controller = new ApplyController(jobseekerProfileManager,
                                     resumeSearchService,
                                     jobSearchService,
                                     jobApplicationSystem,
                                     resumeManager,
                                     myResumeManager);
  }
}
