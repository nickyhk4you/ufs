package com.ufs.campaign.job;

import com.ufs.campaign.annotation.CampaignTemplate;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 */
@CampaignTemplate(templateName="UserFlowTwo")
@Component
public class UserFlowTwoCampaignTemplateJob extends BaseCampaignTemplateTemplateJob {
    private final Logger logger = LoggerFactory.getLogger(UserFlowTwoCampaignTemplateJob.class);

    @Override
    protected void doExecute(JobExecutionContext context) throws JobExecutionException {

        logger.info("UserFlowTwoCampaignTemplateJob...");
    }
}
