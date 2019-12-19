package com.ufs.campaign;

import com.ufs.campaign.service.email.EmailNotifier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UfsApplicationTests {

    @Autowired
    EmailNotifier emailNotifier;

    @Test
    void contextLoads() {
    }


    @Test
    void sendEmail(){
        emailNotifier.send("testMail","khu@adobe.com","");
    }

}
