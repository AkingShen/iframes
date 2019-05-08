package com.iframe.web.scheduler;

import com.iframe.interfaces.dao.testDao.SysCronConfigDao;
import com.iframe.interfaces.dao.testDao.SysDictDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@Configuration
@EnableScheduling
public class DynamicScheduleTask1 implements SchedulingConfigurer {

   @Autowired
   SysCronConfigDao sysCronConfigDao;


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> System.out.println("执行动态定时任务: " + LocalDateTime.now().toLocalTime()),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cronExpress = sysCronConfigDao.getCron().getCronExpress();
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cronExpress)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cronExpress).nextExecutionTime(triggerContext);
                }
        );
    }
}
