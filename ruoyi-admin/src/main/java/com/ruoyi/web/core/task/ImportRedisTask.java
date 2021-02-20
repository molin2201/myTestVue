package com.ruoyi.web.core.task;

import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.web.service.BlackBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ImportRedisTask {
    private static final Logger logger = LoggerFactory.getLogger(ImportRedisTask.class);

    @Scheduled(cron = "0 0/5 * * * ?")
    //0 0/5 * * * ?
    //*/50 * * * * ?
    public void execute() {
        try{
           SpringUtils.getBean(BlackBiz.class).initPhoneCache();
        }catch(Exception ex){
            logger.error("黑名单初始到redis异常",ex);
        }
    }

}
