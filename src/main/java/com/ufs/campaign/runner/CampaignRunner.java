package com.ufs.campaign.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CampaignRunner implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(CampaignRunner.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("***************** CampaignRunner ............");
    }
}
