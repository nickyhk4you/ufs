package com.ufs.campaign.mapper;

import com.ufs.campaign.domain.JobAndTrigger;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobAndTriggerMapper {

    @Results({
            @Result(property = "job_NAME", column = "job_NAME"),
            @Result(property = "job_GROUP", column = "job_GROUP"),
            @Result(property = "job_CLASS_NAME", column = "job_CLASS_NAME"),
            @Result(property = "trigger_NAME", column = "trigger_NAME"),
            @Result(property = "trigger_GROUP", column = "trigger_GROUP"),
            @Result(property = "trigger_STATE", column = "trigger_STATE")
    })
    @Select("select jd.JOB_NAME as job_NAME,jd.JOB_GROUP as job_GROUP,jd.JOB_CLASS_NAME as job_CLASS_NAME," +
            "qz.TRIGGER_NAME as trigger_NAME,qz.TRIGGER_GROUP as trigger_GROUP,  qz.TRIGGER_STATE as trigger_STATE " +
            "from QRTZ_JOB_DETAILS jd, QRTZ_TRIGGERS qz where 1=1 \n" +
            "and jd.SCHED_NAME=qz.SCHED_NAME \n" +
            "and jd.JOB_NAME=qz.JOB_NAME\n" +
            "and jd.JOB_GROUP=qz.JOB_GROUP")
    public List<JobAndTrigger> getJobAndTriggerDetails();

}
