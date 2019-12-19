package com.ufs.campaign.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 */
public interface ICampaignTemplateJob extends Job {
    public void execute(JobExecutionContext context) throws JobExecutionException;

}
