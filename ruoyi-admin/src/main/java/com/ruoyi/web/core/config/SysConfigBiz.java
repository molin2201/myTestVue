package com.ruoyi.web.core.config;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.web.controller.common.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysConfigBiz {

    @Autowired
    public ISysConfigService sysConfigService;


    public String getStrConfig(String key,String def){
       String configV =  sysConfigService.selectConfigByKey(key);
        if(StringUtils.isNotEmpty(configV)){
            return configV;
        }
        return def;
    }
    public Long getLongConfig(String key,Long def){
        String configV =  sysConfigService.selectConfigByKey(key);
        if(StringUtils.isNotEmpty(configV)){
            return Long.parseLong(configV.trim());
        }
        return def;
    }
    public Integer getIntConfig(String key,Integer def){
        String configV =   sysConfigService.selectConfigByKey(key);
        if(StringUtils.isNotEmpty(configV)){
            return Integer.parseInt(configV.trim());
        }
        return def;
    }
}
