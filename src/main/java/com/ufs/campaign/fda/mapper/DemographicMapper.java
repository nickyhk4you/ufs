package com.ufs.campaign.fda.mapper;

import com.ufs.campaign.annotation.DataSource;
import com.ufs.campaign.datasource.DataSourceType;
import com.ufs.campaign.fda.domain.Demographic;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

@Service
public interface DemographicMapper {

    @DataSource(value = DataSourceType.db2)
    @Delete("delete from DEMOGRAPHIC where 1=1")
    void deleteAllDemographic();

    @DataSource(value = DataSourceType.db2)
    @InsertProvider(type = Provider.class, method = "batchInsert")
    int batchInsert(List<Demographic> demographicList);

    class Provider {
        /* 批量插入 */
        public String batchInsert(Map map) {
            List<Demographic> demographicList = (List<Demographic>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO DEMOGRAPHIC (aid,wechatFlag,restaurant_province,restaurant_city_level) VALUES ");
            MessageFormat mf = new MessageFormat(
                    "(#'{'list[{0}].aid}, #'{'list[{0}].wechatFlag}, #'{'list[{0}].restaurant_province},#'{'list[{0}].restaurant_city_level})"
            );
            for (int i = 0; i < demographicList.size(); i++) {
                sb.append(mf.format(new Object[]{i}));
                if (i < demographicList.size() - 1)
                    sb.append(",");
            }
            return sb.toString();
        }
    }
}
