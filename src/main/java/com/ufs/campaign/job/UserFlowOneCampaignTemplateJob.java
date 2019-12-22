package com.ufs.campaign.job;

import com.ufs.campaign.annotation.CampaignTemplate;
import com.ufs.campaign.service.DemoService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@CampaignTemplate(templateName="UserFlowOne")
@DisallowConcurrentExecution
@Component
public class UserFlowOneCampaignTemplateJob extends BaseCampaignTemplateTemplateJob {
    private final Logger logger = LoggerFactory.getLogger(UserFlowOneCampaignTemplateJob.class);


    @Autowired
    private DemoService demoService;

    @Override
    public void doExecute(JobExecutionContext context)
            throws JobExecutionException {
        try {
            logger.info("start the UserFlowOneCampaignTemplateJob .....");
            demoService.run();
        } catch (Exception e) {
            logger.error(e.toString());
        }

    }

}
