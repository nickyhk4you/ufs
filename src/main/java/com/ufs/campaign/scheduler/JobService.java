package com.ufs.campaign.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 0 0 3 * * ?     每天3点执行
 * 0 5 3 * * ?     每天3点5分执行
 * 0 5 3 ? * *     每天3点5分执行，与上面作用相同
 * 0 5/10 3 * * ?  每天3点的 5分，15分，25分，35分，45分，55分这几个时间点执行
 * 0 10 3 ? * 1    每周星期天，3点10分 执行，注：1表示星期天
 * 0 10 3 ? * 1#3  每个月的第三个星期，星期天 执行，#号只能出现在星期的位置
 */
@Component
public class JobService {

//    //表示方法执行完成后5秒
//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayJob() throws InterruptedException {
//        System.out.println("fixedDelay 每隔5秒" + new Date());
//    }

//    //表示每隔5秒
//    @Scheduled(fixedRate = 5000)
//    public void fixedRateJob() {
//
//        System.out.println("fixedRate 每隔5秒" + new Date());
//    }
//
//    //表示每天8时30分0秒执行
//    @Scheduled(cron = "0 0,30 0,8 ? * ? ")
//    public void cronJob() {
//        System.out.println(new Date() + " ...>>cron....");
//    }
}
