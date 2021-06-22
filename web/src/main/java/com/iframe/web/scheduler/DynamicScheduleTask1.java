package com.iframe.web.scheduler;

import com.iframe.interfaces.dao.commonDao.SysCronConfigDao;
import com.iframe.interfaces.dao.systemDao.DistrictInfoDao;
import com.iframe.interfaces.dao.systemDao.HospitalGdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
@Configuration
@EnableScheduling
public class DynamicScheduleTask1 implements SchedulingConfigurer {

   @Autowired
   SysCronConfigDao sysCronConfigDao;

   @Autowired
   DistrictInfoDao districtInfoDao;

   @Autowired
   HospitalGdDao hospitalsInfoDao;


   Map<String,Integer> concurrentHashMap = new ConcurrentHashMap<String,Integer>();



    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//        scheduledTaskRegistrar.addTriggerTask(
//                //1.添加任务内容(Runnable)
//                () -> doExcute(),
//                //2.设置执行周期(Trigger)
//                triggerContext -> {
//                    //2.1 从数据库获取执行周期
//                    String cronExpress = sysCronConfigDao.getCron().getCronExpress();
//                    //2.2 合法性校验.
//                    if (StringUtils.isEmpty(cronExpress)) {
//                        // Omitted Code ..
//                    }
//                    //2.3 返回执行周期(Date)
//                    return new CronTrigger(cronExpress).nextExecutionTime(triggerContext);
//                }
//        );

    }

//    public void doExcute(){
//        int i= 0;
//        System.out.println("=================开启外购数据匹配===================");
//        d
//    }


}
