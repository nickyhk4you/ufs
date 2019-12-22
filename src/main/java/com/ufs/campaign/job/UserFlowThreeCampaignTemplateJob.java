package com.ufs.campaign.job;

import com.ufs.campaign.annotation.CampaignTemplate;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@CampaignTemplate(templateName="UserFlowThree")
@Component
public class UserFlowThreeCampaignTemplateJob extends BaseCampaignTemplateTemplateJob{
    private final Logger logger = LoggerFactory.getLogger(UserFlowThreeCampaignTemplateJob.class);

    @Override
    protected void doExecute(JobExecutionContext context) throws JobExecutionException {
        logger.info("UserFlowThreeCampaignTemplateJob...");


    }
}
