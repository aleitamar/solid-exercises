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
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeRepository;

public class TestIt
{
  private static final int INVALID_JOB_ID        = 555;
  private static final String SHARED_RESUME_NAME = "A Resume";
  private static final int JOBSEEKER_WITH_RESUME = 777;
  private static final int INCOMPLETE_JOBSEEKER  = 888;
  private static final int APPROVED_JOBSEEKER    = 1010;

  private ApplyWithFileController    applyWithFileController;
  private ApplyWithResumeController  applyWithResumeController;
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
    Jobseeker JOBSEEKER = createJobseeker(APPROVED_JOBSEEKER, true, jobseekerProfileManager);
    HttpSession session = new HttpSession(JOBSEEKER);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","5");

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    applyWithFileController.handle(request, response, SHARED_RESUME_NAME);

    assertEquals("success", response.getResultType());
  }

  @Test
  public void requestWithValidJobByBasic()
  {
    JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    Jobseeker JOBSEEKER = createJobseeker(APPROVED_JOBSEEKER, false, jobseekerProfileManager);
    HttpSession session = new HttpSession(JOBSEEKER);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","5");

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    applyWithFileController.handle(request, response, SHARED_RESUME_NAME);

    assertEquals("success", response.getResultType());
  }

  @Test
  public void applyUsingExistingResume()
  {
    JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    Jobseeker JOBSEEKER = createJobseeker(JOBSEEKER_WITH_RESUME, true, jobseekerProfileManager);
    HttpSession session = new HttpSession(JOBSEEKER);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","5");
    parameters.put("whichResume", "existing");

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    applyWithResumeController.handle(request, response);

    assertEquals("success", response.getResultType());
  }

  @Test
  public void requestWithInvalidJob()
  {
    JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    Jobseeker JOBSEEKER = createJobseeker(APPROVED_JOBSEEKER, true, jobseekerProfileManager);
    HttpSession session = new HttpSession(JOBSEEKER);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId", String.valueOf(INVALID_JOB_ID));

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    applyWithFileController.handle(request, response, SHARED_RESUME_NAME);

    assertEquals("invalidJob", response.getResultType());
  }

  @Test
  public void requestWithNoResume()
  {
    JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    Jobseeker JOBSEEKER = createJobseeker(APPROVED_JOBSEEKER, true, jobseekerProfileManager);
    HttpSession session = new HttpSession(JOBSEEKER);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId", "5");

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    applyWithFileController.handle(request, response, null);

    assertEquals("error", response.getResultType());
  }

  @Test
  public void reapplyToJob()
  {
    JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    Jobseeker JOBSEEKER = createJobseeker(APPROVED_JOBSEEKER, true, jobseekerProfileManager);
    HttpSession session = new HttpSession(JOBSEEKER);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","15");

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    applyWithFileController.handle(request, response, SHARED_RESUME_NAME);

    assertEquals("error", response.getResultType());
  }

  @Test
  public void unapprovedBasic()
  {
    JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    Jobseeker JOBSEEKER = createJobseeker(INCOMPLETE_JOBSEEKER, false, jobseekerProfileManager);
    HttpSession session = new HttpSession(JOBSEEKER);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","5");

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    applyWithFileController.handle(request, response, SHARED_RESUME_NAME);

    assertEquals("completeResumePlease", response.getResultType());
  }

  @Test
  public void resumeIsSaved()
  {
    JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    Jobseeker JOBSEEKER = createJobseeker(APPROVED_JOBSEEKER, true, jobseekerProfileManager);
    HttpSession session = new HttpSession(JOBSEEKER);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","5");

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    applyWithFileController.handle(request, response, SHARED_RESUME_NAME);

    assertTrue(resumeRepository.contains(new Resume(SHARED_RESUME_NAME)));
  }

  @Test
  public void resumeIsMadeActive()
  {
    JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    Jobseeker JOBSEEKER = createJobseeker(APPROVED_JOBSEEKER, true, jobseekerProfileManager);
    HttpSession session = new HttpSession(JOBSEEKER);

    Map<String, String> parameters = new HashMap<>();
    parameters.put("jobId","5");
    parameters.put("makeResumeActive", "yes");

    HttpRequest request = new HttpRequest(session, parameters);

    HttpResponse response = new HttpResponse();

    applyWithFileController.handle(request, response, "Save Me Seymour");

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
  }

  private JobseekerProfile addToJobseekerProfileRepository(Jobseeker jobseeker, ProfileStatus status)
  {
    JobseekerProfile profile = new JobseekerProfile(jobseeker, status);
    jobseekerProfileRepository.addProfile(profile);
    return profile;
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
    Jobseeker JOBSEEKER = createJobseeker(APPROVED_JOBSEEKER, true, jobseekerProfileManager);
    Job job = new Job(15);
    Resume resume = new Resume("foo");

    existingApplication = new SuccessfulApplication(JOBSEEKER, job, resume);

    jobApplicationRepository.add(existingApplication);
  }

  private void setupController()
  {
    JobSearchService jobSearchService = new JobSearchService(jobRepository);
    JobApplicationSystem jobApplicationSystem = new JobApplicationSystem(jobApplicationRepository);
    ResumeManager resumeManager = new ResumeManager(activeResumeRepository, resumeRepository);

    applyWithFileController = new ApplyWithFileController(jobSearchService,
                                     jobApplicationSystem,
                                     resumeManager);
    
    applyWithResumeController = new ApplyWithResumeController(jobSearchService,
                                     jobApplicationSystem,
                                     resumeManager);
  }
  
  private Jobseeker createJobseeker(int id, Boolean isPremium, JobseekerProfileManager jobseekerProfileManager)
  {
    Jobseeker jobseeker = new Jobseeker(id, isPremium, jobseekerProfileManager);
    ProfileStatus status = ProfileStatus.APPROVED;
    switch (id) 
    {
      case APPROVED_JOBSEEKER:
        status = ProfileStatus.APPROVED;
        break;
      case INCOMPLETE_JOBSEEKER:
        status = ProfileStatus.INCOMPLETE;
        break;
      case JOBSEEKER_WITH_RESUME:
        status = ProfileStatus.APPROVED;
        break;
      default:
        status = ProfileStatus.APPROVED;
        break;
    }
    addToJobseekerProfileRepository(jobseeker, status);
    return jobseeker;
  }
}