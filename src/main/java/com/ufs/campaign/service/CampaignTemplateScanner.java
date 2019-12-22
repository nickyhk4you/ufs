package com.ufs.campaign.service;


import com.ufs.campaign.annotation.CampaignTemplate;
import com.ufs.campaign.job.ICampaignTemplateJob;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CampaignTemplateScanner implements ApplicationContextAware {

    ApplicationContext applicationContext;

    public Map<String, ICampaignTemplateJob> findCandidateCampaignTemplate() {
        final Map<String, ICampaignTemplateJob> campaignTemplateJobMap = new HashMap<>();
        Map<String, Object> jobMap = applicationContext.getBeansWithAnnotation(CampaignTemplate.class);
        for (Map.Entry entry : jobMap.entrySet()) {
            CampaignTemplate findable = entry.getValue().getClass().getAnnotation(CampaignTemplate.class);
            ICampaignTemplateJob job = (ICampaignTemplateJob) entry.getValue();
            String templateName = findable.templateName();
            campaignTemplateJobMap.put(templateName, job);
        }
        return campaignTemplateJobMap;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
