package com.ufs.campaign;

import com.ufs.campaign.csv.CSVReaderWriter;
import com.ufs.campaign.domain.UFSTrialServiceDTO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CampaignCSVTest {

    private final Logger logger = LoggerFactory.getLogger(CampaignCSVTest.class);

    @Autowired
    CSVReaderWriter csvReaderWriter;


    @Test
    public void testReadCSVFile() throws FileNotFoundException {
        File f = ResourceUtils.getFile("classpath:csv/Mini-site_SKU1104_new.csv");
        List<UFSTrialServiceDTO> ufsTrialServiceDTOList = csvReaderWriter.readCSVWithHeader(f.getAbsolutePath(), UFSTrialServiceDTO.class);

    }

    @Test
    public void testWriteCSVFile() throws FileNotFoundException {
        File f = ResourceUtils.getFile("classpath:csv/Mini-site_SKU1104_new.csv");
        List<UFSTrialServiceDTO> ufsTrialServiceDTOList = csvReaderWriter.readCSVWithHeader(f.getAbsolutePath(), UFSTrialServiceDTO.class);
        String[] header = {"skuCode", "skuName", "trialServiceUrl"};
        List<Object> objectList = new ArrayList<Object>();
        objectList.addAll(ufsTrialServiceDTOList);
        File outputFile = csvReaderWriter.writeCsv(objectList, header, "trialServiceOutput.csv");
        logger.info(outputFile.getName() + " - " + outputFile.getAbsolutePath());

    }
}
