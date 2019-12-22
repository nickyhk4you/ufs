package com.ufs.campaign.job;

import com.ufs.campaign.annotation.CampaignTemplate;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 *
 */
@CampaignTemplate(templateName="UserFlowTwo")
@Component
public class UserFlowTwoCampaignTemplateJob extends BaseCampaignTemplateTemplateJob {

    @Override
    protected void doExecute(JobExecutionContext context) throws JobExecutionException {

    }
}
