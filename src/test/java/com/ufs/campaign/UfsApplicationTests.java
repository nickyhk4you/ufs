package com.ufs.campaign;

import com.ufs.campaign.smtp.SMTPEmailNotifier;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UfsApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void sendEmail(){
        SMTPEmailNotifier.send("testMail","khu@adobe.com","");
    }

}
