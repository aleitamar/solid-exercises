package com.theladders.solid.isp.oldjob;

public interface JobCompany
{
  /**
   * @return the name of the company
   */
  String getCompany();

  /**
   * Gets the value of the company_size_id field.
   * This represents the id in the company size table for the description of
   * how large the company is.
   *
   * @return companySize
   */
  Integer getCompanySize();
}
