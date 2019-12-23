package com.ufs.campaign;

import com.ufs.campaign.domain.UFSTrialServiceDTO;
import com.ufs.campaign.fda.domain.Demographic;
import com.ufs.campaign.fda.mapper.DemographicMapper;
import com.ufs.campaign.mapper.TrialServiceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class CampaignFDATest {

    @Autowired
    DemographicMapper demographicMapper;

    @Autowired
    TrialServiceMapper trialServiceMapper;


    @Test
    public void fdaTest() {
        demographicMapper.deleteAllDemographic();

        List<Demographic> demographicList = new ArrayList<Demographic>();
        for (int i = 0; i < 10; i++) {
            Random ran1 = new Random(10);
            Demographic demographic = new Demographic();
            demographic.setAid("aid_"+ran1.nextInt() + i);
            demographic.setWechatFlag("wechat_"+i);
            demographic.setRestaurant_city_level("level_" + i);
            demographic.setRestaurant_county("country_" + i);
            demographic.setRestaurant_province("province_" + i);
            demographicList.add(demographic);
        }

        demographicMapper.batchInsert(demographicList);

        trialServiceMapper.deleteAllTrialService();


        List<UFSTrialServiceDTO> ufsTrialServiceDTOList = new ArrayList<UFSTrialServiceDTO>();
        for(int i=0;i<10;i++){
            Random ran1 = new Random(10);

            UFSTrialServiceDTO ufsTrialServiceDTO = new UFSTrialServiceDTO();
            ufsTrialServiceDTO.setStdskucode("skucode_"+ran1.nextInt() +i);
            ufsTrialServiceDTO.setStdskuname("stdskuname_"+i);
            ufsTrialServiceDTO.setUrl("www.ufs.com/demo_url_"+i);
            ufsTrialServiceDTOList.add(ufsTrialServiceDTO);
        }
        trialServiceMapper.batchInsert(ufsTrialServiceDTOList);

    }
}
