package com.ufs.campaign.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.ufs.campaign.domain.JobAndTrigger;
import com.ufs.campaign.job.ICampaignTemplateJob;
import com.ufs.campaign.service.JobAndTriggerSevice;
import com.ufs.campaign.util.LogWriter;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/job")
public class JobController {

    private final Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private JobAndTriggerSevice jobAndTriggerSevice;
    //加入Qulifier注解，通过名称注入bean
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    public static ICampaignTemplateJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (ICampaignTemplateJob) class1.newInstance();
    }

    @PostMapping(value = "/addjob")
    public void addJob(@RequestParam(value = "jobClassName") String jobClassName,
                       @RequestParam(value = "jobGroupName") String jobGroupName,
                       @RequestParam(value = "cronExpression") String cronExpression,
                       @RequestParam(value = "description") String description) throws Exception {

        Preconditions.checkNotNull(StringUtils.isEmpty(jobClassName), "jobClassName is null");
        Preconditions.checkNotNull(StringUtils.isEmpty(jobGroupName), "jobGroupName is null");

        // 启动调度器
        scheduler.start();
        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).withDescription(description).build();

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                .withSchedule(scheduleBuilder).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            logger.error("创建定时任务失败: "+e );
        }

    }

    @PostMapping(value = "/pausejob")
    public void pauseJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {

        try {
            scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            //throw new Exception("创建定时任务失败");
            logger.error("停止定时任务失败: "+e );
        }

    }

    @PostMapping(value = "/resumejob")
    public void resumeJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {

        try {
            scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            logger.error("继续定时任务失败: "+e );
        }

    }

    @PostMapping(value = "/deletejob")
    public void deleteJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {

        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            logger.error("删除定时任务失败: "+e );
        }

    }

    /**
     * 查询任务列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/queryjob")
    public Map<String, Object> queryjob(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            PageInfo<JobAndTrigger> jobAndTrigger = jobAndTriggerSevice.getJobAndTriggerDetails(pageNum, pageSize);
            map.put("JobAndTrigger", jobAndTrigger);
            map.put("number", jobAndTrigger.getTotal());

        } catch (Exception e) {
            LogWriter.writeErrorLog("查询定时任务列表失败", e);
        }

        return map;
    }

    /**
     * 修改定时任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @param description
     * @throws Exception
     */
    @PostMapping(value = "/reschedulejob")
    public void rescheduleJob(@RequestParam(value = "jobClassName") String jobClassName,
                              @RequestParam(value = "jobGroupName") String jobGroupName,
                              @RequestParam(value = "cronExpression") String cronExpression,
                              @RequestParam(value = "description") String description) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).withDescription(description).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            LogWriter.writeErrorLog("更新定时任务失败", e);
        }
    }

}
