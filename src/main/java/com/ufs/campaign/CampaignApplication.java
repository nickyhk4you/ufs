package com.ufs.campaign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = "com.ufs.campaign.mapper,com.ufs.campaign.fda.mapper")
public class CampaignApplication {
    public static void main(String[] args) {
        SpringApplication.run(CampaignApplication.class, args);
    }
}
