package com.ufs.campaign;

import com.ufs.campaign.csv.CSVReaderWriter;
import com.ufs.campaign.domain.UFSTrialServiceDTO;
import com.ufs.campaign.fda.domain.Demographic;
import com.ufs.campaign.fda.mapper.DemographicMapper;
import com.ufs.campaign.mapper.TrialServiceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@SpringBootTest
public class CampaignFDATest {

    @Autowired
    DemographicMapper demographicMapper;

    @Autowired
    TrialServiceMapper trialServiceMapper;

    @Autowired
    CSVReaderWriter csvReaderWriter;

    @Test
    public void fdaTest() throws FileNotFoundException {
        demographicMapper.deleteAllDemographic();
        trialServiceMapper.deleteAllTrialService();

        File demographicFile = ResourceUtils.getFile("classpath:csv/Demographic.csv");
        List<Demographic> demographicList = csvReaderWriter.readCSVWithHeader(demographicFile.getAbsolutePath(), Demographic.class);

        demographicMapper.batchInsert(demographicList);


        File f = ResourceUtils.getFile("classpath:csv/Mini-site_SKU1104_new.csv");
        List<UFSTrialServiceDTO> ufsTrialServiceDTOList = csvReaderWriter.readCSVWithHeader(f.getAbsolutePath(), UFSTrialServiceDTO.class);

        trialServiceMapper.batchInsert(ufsTrialServiceDTOList);
    }


}
