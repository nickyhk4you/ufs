package com.ufs.campaign.service;

import com.ufs.campaign.util.LogWriter;

public class BaseService {
    /**
     * 作业编号
     */
    protected String jobId;
    /**
     * 作业名称
     */
    protected String jobName;
    /**
     * 执行成功数量
     */
    protected int success = 0;
    /**
     * 执行失败数量
     */
    protected int fail = 0;
    /**
     * 待处理记录总数
     */
    protected int total = 0;

    /**
     * 设置job编号
     */
    protected void setJobId(String jId) {
        jobId = jId;
    }

    /**
     * 设置job名称
     *
     * @param jName
     */
    protected void setJobName(String jName) {
        jobName = jName;
    }

    /**
     * 开始作业方法
     */
    protected void startJob() {
        success = 0;
        fail = 0;
        total = 0;
        LogWriter.writeJobStartLog(jobId, jobName);
    }

    /**
     * 设置作业待处理总数
     *
     * @param tl
     */
    protected void setJobTotal(int tl) {
        total = tl;
        LogWriter.writeJobStartLog(jobId, jobName, total);
    }

    /**
     * 当前项处理状态
     *
     * @param currentKey
     */
    protected void setSuccessJob(String currentKey) {
        success++;
        String msg = String.format("jobsrun - jobid：%s jobname:%s key:%s status:success", jobId, jobName, currentKey);
        LogWriter.writeWorkLog(msg);
    }

    /**
     * 失败处理
     *
     * @param currentKey
     */
    protected void setFailJob(String currentKey) {
        fail++;
        String msg = String.format("jobsrun - jobid：%s jobname:%s key:%s status:fail", jobId, jobName, currentKey);
        LogWriter.writeWorkLog(msg);
    }

    /**
     * 结束作业
     */
    protected void endJob() {
        LogWriter.writeJobEndLog(jobId, jobName, total, success, fail);
    }
}
