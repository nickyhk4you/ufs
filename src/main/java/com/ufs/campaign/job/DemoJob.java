package com.ufs.campaign.job;

import com.ufs.campaign.service.DemoService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@DisallowConcurrentExecution
public class DemoJob implements IBaseJob {
    private final Logger logger = LoggerFactory.getLogger(DemoJob.class);


    @Autowired
    private DemoService demoService;

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        try {
            demoService.run();
        } catch (Exception e) {
            logger.error(e.toString());
        }

    }

}
