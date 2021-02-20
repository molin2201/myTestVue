package com.ruoyi;

import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.web.service.BlackBiz;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableScheduling // 开启定时任务功能
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)ﾞ  ") ;
        SpringUtils.getBean(BlackBiz.class).initCache();
        System.out.println("(♥◠‿◠)ﾉﾞ  初始成功   ლ(´ڡ`ლ)ﾞ  ") ;
    }
}
