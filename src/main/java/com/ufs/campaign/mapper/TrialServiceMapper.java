package com.ufs.campaign.mapper;


import com.ufs.campaign.domain.UFSTrialServiceDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

@Service
public interface TrialServiceMapper {
    @Select("select * from TRIALSERVICE")
    List<UFSTrialServiceDTO> getAllTrialService();

    @Delete("delete from TRIALSERVICE where 1=1")
    void deleteAllTrialService();

    @Delete("delete from TRIALSERVICE where stdskucode=#{stdskucode}")
    void deleteTrialServiceById(String stdskucode);


    @InsertProvider(type = Provider.class, method = "batchInsert")
    int batchInsert(List<UFSTrialServiceDTO> ufsTrialServiceDTOList);

    class Provider {
        /* 批量插入 */
        public String batchInsert(Map map) {
            List<UFSTrialServiceDTO> ufsTrialServiceDTOList = (List<UFSTrialServiceDTO>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO TRIALSERVICE (stdskucode,stdskuname,url) VALUES ");
            MessageFormat mf = new MessageFormat(
                    "(#'{'list[{0}].stdskucode}, #'{'list[{0}].stdskuname}, #'{'list[{0}].url})"
            );
            for (int i = 0; i < ufsTrialServiceDTOList.size(); i++) {
                sb.append(mf.format(new Object[]{i}));
                if (i < ufsTrialServiceDTOList.size() - 1)
                    sb.append(",");
            }
            return sb.toString();
        }
    }
}
