package com.theladders.solid.isp.oldjob;

import com.theladders.solid.isp.oldjob.stubs.Discipline;
import com.theladders.solid.isp.oldjob.stubs.Experience;
import com.theladders.solid.isp.oldjob.stubs.Industry;

import java.util.List;

/**
 * User: tsymborski
 * Date: 2/13/13
 * Time: 5:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class AwesomeJob extends JobImpl {
    private JobCompanyImpl      jobCompanyImpl;
    private JobCompensationImpl jobCompensationImpl;
    private JobEventDateImpl    jobEventDateImpl;
    private JobDecoratorImpl    jobDecoratorImpl;
    private JobSettingsImpl     jobSettingsImpl;
    private JobLocationImpl     jobLocationImpl;

    private List<Discipline>    disciplines;
    private Experience          experience;
    private Industry            industry;
    private int                 oldJobId     = 0;
    private String              reportsTo;
    private int                 subscriberId = 0;

    public AwesomeJob(JobCompanyImpl jobCompanyImpl,
                      JobCompensationImpl jobCompensationImpl,
                      JobEventDateImpl jobEventDateImpl,
                      JobDecoratorImpl jobDecoratorImpl,
                      JobSettingsImpl jobSettingsImpl,
                      JobLocationImpl jobLocationImpl,
                      List<Discipline> disciplines,
                      Experience experience,
                      Industry industry,
                      int oldJobId,
                      String reportsTo,
                      int subscriberId)
    {
        this.jobCompanyImpl = jobCompanyImpl;
        this.jobCompensationImpl = jobCompensationImpl;
        this.jobEventDateImpl = jobEventDateImpl;
        this.jobDecoratorImpl = jobDecoratorImpl;
        this.jobSettingsImpl = jobSettingsImpl;
        this.jobLocationImpl = jobLocationImpl;
        this.disciplines = disciplines;
        this.experience = experience;
        this.industry = industry;
        this.oldJobId = oldJobId;
        this.reportsTo = reportsTo;
        this.subscriberId = subscriberId;
    }


}
