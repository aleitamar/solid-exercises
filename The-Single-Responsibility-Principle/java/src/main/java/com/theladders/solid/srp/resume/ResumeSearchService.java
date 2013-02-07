package com.theladders.solid.srp.resume;

import com.theladders.solid.srp.resume.ResumeRepository;

public class ResumeSearchService {
  private final ResumeRepository repository;

  public ResumeSearchService(ResumeRepository repository)
  {
    this.repository = repository;
  }
}