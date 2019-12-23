package com.ufs.campaign.springscheduler;

import com.ufs.campaign.domain.UFSTrialServiceDTO;
import com.ufs.campaign.mapper.TrialServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 0 0 3 * * ?     每天3点执行
 * 0 5 3 * * ?     每天3点5分执行
 * 0 5 3 ? * *     每天3点5分执行，与上面作用相同
 * 0 5/10 3 * * ?  每天3点的 5分，15分，25分，35分，45分，55分这几个时间点执行
 * 0 10 3 ? * 1    每周星期天，3点10分 执行，注：1表示星期天
 * 0 10 3 ? * 1#3  每个月的第三个星期，星期天 执行，#号只能出现在星期的位置
 */
@Component
public class ImporterService {

    private final Logger logger = LoggerFactory.getLogger(ImporterService.class);

    @Autowired
    private TrialServiceMapper trialServiceMapper;

    //表示每隔60秒
    @Scheduled(fixedRate = 60000)
    public void refreshTrialServiceTable() {
        logger.info("fixedRate 每隔60秒" + new Date());
        trialServiceMapper.deleteAllTrialService();
        List<UFSTrialServiceDTO> ufsTrialServiceDTOList = new ArrayList<UFSTrialServiceDTO>();
        for (int i = 0; i < 100; i++) {
            UFSTrialServiceDTO ufsTrialServiceDTO = new UFSTrialServiceDTO();
            ufsTrialServiceDTO.setStdskucode("skucode_" + i);
            ufsTrialServiceDTO.setStdskuname("stdskuname_" + i);
            ufsTrialServiceDTO.setUrl("ufs.com/demo_url_" + i);
            ufsTrialServiceDTOList.add(ufsTrialServiceDTO);
        }
        trialServiceMapper.batchInsert(ufsTrialServiceDTOList);
    }


}
