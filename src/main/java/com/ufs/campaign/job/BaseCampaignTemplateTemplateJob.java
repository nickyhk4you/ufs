package com.ufs.campaign.job;

import com.ufs.campaign.service.email.EmailNotifier;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public abstract class BaseCampaignTemplateTemplateJob implements ICampaignTemplateJob {

    private final Logger logger = LoggerFactory.getLogger(UserFlowOneCampaignTemplateJob.class);

    @Autowired
    EmailNotifier emailNotifier;

    protected void preBaseCampaignTemplate(JobExecutionContext context) throws JobExecutionException {
        logger.info("start ...");
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        preBaseCampaignTemplate(context);
        doExecute(context);
    }

    protected abstract void doExecute(JobExecutionContext context) throws JobExecutionException;
}
