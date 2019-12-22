package com.ufs.campaign;

import com.ufs.campaign.service.CampaignTemplateScanner;
import com.ufs.campaign.service.email.EmailNotifier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CampaignApplicationTests {

    @Autowired
    EmailNotifier emailNotifier;

    @Autowired
    CampaignTemplateScanner scanner;

    @Test
    void contextLoads() {
    }


    @Test
    void sendEmail() {
        emailNotifier.send("testMail", "khu@adobe.com", "");
    }

    @Test
    void findClazz() {
        scanner.findCandidateCampaignTemplate();
    }
}
