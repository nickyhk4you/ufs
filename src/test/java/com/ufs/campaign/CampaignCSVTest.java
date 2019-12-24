package com.ufs.campaign;

import com.ufs.campaign.csv.CSVReader;
import com.ufs.campaign.domain.UFSTrialServiceDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@SpringBootTest
public class CampaignCSVTest {

    @Autowired
    CSVReader csvReader;


    @Test
    public void testReadCSVFile() throws FileNotFoundException {
        File f = ResourceUtils.getFile("classpath:csv/Mini-site_SKU1104_new.csv");
        List<UFSTrialServiceDTO> ufsTrialServiceDTOList = csvReader.readCSVWithHeader(f.getAbsolutePath(), UFSTrialServiceDTO.class);


    }
}
