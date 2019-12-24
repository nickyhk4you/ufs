package com.ufs.campaign;

import com.ufs.campaign.csv.CSVReader;
import com.ufs.campaign.domain.UFSTrialServiceDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CampaignCSVTest {

    @Autowired
    CSVReader csvReader;


    @Test
    public void testReadCSVFile() {
        String[] header = {"skuCode", "skuName", "trialServiceUrl"};
        List<UFSTrialServiceDTO> ufsTrialServiceDTOList = csvReader.readCSV(UFSTrialServiceDTO.class);

    }
}
