package com.ruoyi.web.service;

import com.ruoyi.common.core.redis.RedisCache;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.common.SystemConst;

import com.ruoyi.web.controller.common.RedisKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CountBiz {
    private static final Logger logger = LoggerFactory.getLogger(CountBiz.class);

    @Autowired
    public RedisCache redisCache;

    /**
     * 接口调用统计
     * @param userCode
     */
    public void interfaceCount(String userCode,String curDate){
        try{
            redisCache.addCount(getKey(RedisKey.COUNT,SystemConst.INNER_VERSION,".callCount"));
            redisCache.reduceCount(getKey(RedisKey.COUNT,SystemConst.INNER_VERSION,".balCount"));
            redisCache.setCacheMapValue(getKey(RedisKey.COUNT,SystemConst.INNER_VERSION,".user"),userCode,userCode);

            redisCache.addCount(getKey(RedisKey.COUNT,SystemConst.INNER_VERSION,".callCount.",curDate));
            redisCache.reduceCount(getKey(RedisKey.COUNT,SystemConst.INNER_VERSION,".balCount.",curDate));
            redisCache.setCacheMapValue(getKey(RedisKey.COUNT,SystemConst.INNER_VERSION,".user.",curDate),userCode,userCode);
        }catch(Exception ex){
            logger.debug("统计异常",ex);
        }
    }
    public void interfaceHitCount(String userCode,String curDate){
        try{
            redisCache.addCount(getKey(RedisKey.COUNT,SystemConst.INNER_VERSION,".hitCount"));
            redisCache.addCount(getKey(RedisKey.COUNT,SystemConst.INNER_VERSION,".hitCount.",curDate));
        }catch(Exception ex){
            logger.debug("统计异常",ex);
        }
    }

    public void thirdInterfaceCount(String userCode,String interfaceId,String curDate){
        try{
            redisCache.addCount(getKey(RedisKey.COUNT,"thirdInterface.callCount.",interfaceId,".",curDate));
            redisCache.reduceCount(getKey(RedisKey.COUNT,"thirdInterface.balCount.",interfaceId,".",curDate));
            redisCache.setCacheMapValue(getKey(RedisKey.COUNT,"thirdInterface.user.",interfaceId,".",curDate),userCode,userCode);

            redisCache.addCount(getKey(RedisKey.COUNT,"thirdInterface.callCount.",interfaceId));
            redisCache.reduceCount(getKey(RedisKey.COUNT,"thirdInterface.balCount.",interfaceId));
            redisCache.setCacheMapValue(getKey(RedisKey.COUNT,"thirdInterface.user.",interfaceId),userCode,userCode);
        }catch(Exception ex){
            logger.debug("统计异常",ex);
        }
    }
    public void thirdInterfaceHitCount(String userCode,String interfaceId,String curDate){
        redisCache.addCount(getKey(RedisKey.COUNT,"thirdInterface.hitCount.",interfaceId));
        redisCache.addCount(getKey(RedisKey.COUNT,"thirdInterface.hitCount.",interfaceId,".",curDate));

    }

    private String getKey(String... keys){
        StringBuffer s=new StringBuffer();
        for(String stemp:keys){
            s.append(stemp);
        }
        return s.toString();
    }


}
