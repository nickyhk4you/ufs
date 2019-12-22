package com.ufs.campaign.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ufs.campaign.mapper.JobAndTriggerMapper;
import com.ufs.campaign.domain.JobAndTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JobAndTriggerSevice {
    @Autowired
    private JobAndTriggerMapper jobAndTriggerMapper;

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobAndTrigger> list = jobAndTriggerMapper.getJobAndTriggerDetails();
        PageInfo<JobAndTrigger> page = new PageInfo<JobAndTrigger>(list);
        return page;
    }

}
