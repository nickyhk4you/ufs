package com.ufs.campaign;

import com.ufs.campaign.domain.UFSTrialServiceDTO;
import com.ufs.campaign.mapper.TrialServiceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CampaignDumpTrialServiceTest {


    @Autowired
    TrialServiceMapper trialServiceMapper;

    @Test
    public void deleteTrialServiceData(){
        trialServiceMapper.deleteAllTrialService();
    }

    @Test
    public void dumpTrialServiceData(){
        List<UFSTrialServiceDTO> ufsTrialServiceDTOList = new ArrayList<UFSTrialServiceDTO>();
        for(int i=0;i<100;i++){
            UFSTrialServiceDTO ufsTrialServiceDTO = new UFSTrialServiceDTO();
            ufsTrialServiceDTO.setStdskucode("skucode_"+i);
            ufsTrialServiceDTO.setStdskuname("stdskuname_"+i);
            ufsTrialServiceDTO.setUrl("www.ufs.com/demo_url_"+i);
            ufsTrialServiceDTOList.add(ufsTrialServiceDTO);
        }
        trialServiceMapper.batchInsert(ufsTrialServiceDTOList);
    }
}
