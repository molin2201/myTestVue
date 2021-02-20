package com.ruoyi.web.service;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.core.redis.RedissonCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.common.SystemConst;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.domain.vo.AzRuleVo;
import com.ruoyi.system.service.*;
import com.ruoyi.web.controller.black.BlackReq;

import com.ruoyi.web.controller.common.RedisKey;
import com.ruoyi.web.controller.common.SysConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BlackBiz {
    private static final Logger logger = LoggerFactory.getLogger(BlackBiz.class);

    @Autowired
    public IAzPhoneBlackService blackService;
    @Autowired
    public IAzPhoneWhiteService whitleService;
    @Autowired
    public IAzInterfaceService interfaceService;
    @Autowired
    public ISysConfigService sysConfigService;
    @Autowired
    public RedisCache redisCache;

    @Autowired
    public IAzRuleService ruleService;

    @Autowired
    public  IAzPhoneareaService phoneareaService;

    @Autowired
    public RedissonCache redissonCache;
    private RestTemplate getTemplate(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(15000);
        factory.setReadTimeout(10000);
        return new RestTemplate(factory);
    }

    // 订阅
    @PostConstruct
    public void subscribe(){

    }

    //发布
    public long publish(String url, BlackReq req){
      return 1l;
    }

    public void initPhoneCache(){
      //  initPriBlackPhone();
        initPriWhitePhone();
        initPubWhitePhone();
        initPubBlackPhone();
        System.out.println("初始缓存完成");
    }

    /**
     * 初始系统配置
     */
    public void initCache(){
        initThirdInterface();
        System.out.println("初始第三方接口完成");
        initRule();
        System.out.println("初始过滤规则完成");
        initInterfaceCountCache();
        System.out.println("初始接口统计数据完成");

    }




    private List<String>  toList(String str){
        List<String> list =new ArrayList<>();
        if(StringUtils.isNotEmpty(str)){
            String[] areas =str.split(",");
            for(String s:areas){
                if(StringUtils.isNotEmpty(s.trim())){
                    list.add(s.trim());
                }
            }
        }
        return list;
    }
    /**
     * 初始用户规则
     */
    public void initRule(){
        redisCache.deleteObject(RedisKey.RULE);
        AzRule azRule=new AzRule();
        azRule.setStatus(SystemConst.Y);
        List<AzRule> interfaceList=   ruleService.selectAzRuleList(azRule);
        if(interfaceList!=null && interfaceList.size()>0){
           Map<String, AzRuleVo> userRule=new HashMap<String,AzRuleVo>();
            for(AzRule azRuleTemp:interfaceList){
                if(userRule.containsKey(azRuleTemp.getCreateUserId())){
                    AzRuleVo  azRuleVo  =   userRule.get(azRuleTemp.getCreateUserId());
                    azRuleVo.getAreas().addAll(toList(azRuleTemp.getAreaId()));
                    azRuleVo.getCompanys().addAll(toList(azRuleTemp.getCompanyId()));
                    azRuleVo.getPhonePattern().addAll(toList(azRuleTemp.getNumruleId()));
                }else{
                    AzRuleVo  azRuleVo =new AzRuleVo();
                    azRuleVo.setAreas(new ArrayList<>());
                    azRuleVo.setCompanys(new ArrayList<>());
                    azRuleVo.setPhonePattern(new ArrayList<>());
                    azRuleVo.getAreas().addAll(toList(azRuleTemp.getAreaId()));
                    azRuleVo.getCompanys().addAll(toList(azRuleTemp.getCompanyId()));
                    azRuleVo.getPhonePattern().addAll(toList(azRuleTemp.getNumruleId()));
                    userRule.put(azRuleTemp.getCreateUserId(),azRuleVo);
                }
            }
            for(String key:userRule.keySet()){
                AzRuleVo  azRuleVo = userRule.get(key);
                AzPhonearea azPhonearea=new AzPhonearea();
                Map<String,Object> parmas=new HashMap<String,Object>();
                if(azRuleVo.getAreas().size()>0){
                    parmas.put("areaCodes",azRuleVo.getAreas()) ;
                }
                if(azRuleVo.getCompanys().size()>0){
                    parmas.put("companys",azRuleVo.getCompanys()) ;
                }
                azPhonearea.setParams(parmas);
                List<AzPhonearea> phoneArezList  =   phoneareaService.selectAzPhoneareaList(azPhonearea);
                List<String> phonePres=new ArrayList<>();
                if(phoneArezList!=null && phoneArezList.size()>0){
                    for(AzPhonearea azPhoneareaTemp:phoneArezList){
                        phonePres.add(azPhoneareaTemp.getPrePhone()) ;
                    }
                }
                azRuleVo.setPhonePre(phonePres);
                redisCache.setCacheMapValue(RedisKey.RULE, key, userRule.get(key));
            }
        }
    }
    /**
     * 初始第三方接口计数
     */
    public void initInterfaceCountCache(){
        AzInterface azInterface=new AzInterface();
        azInterface.setStatus(SystemConst.Y);
        List<AzInterface> interfaceList=   interfaceService.selectAzInterfaceList(azInterface);
        boolean isClearUser=false;
        if(interfaceList!=null && interfaceList.size()>0){
            for(AzInterface tempInterface:interfaceList){
                String version =  tempInterface.getVersion();
                if(!SystemConst.INNER_VERSION.equals(version)){
                    Long value =   redisCache.addCount(RedisKey.COUNT+"thirdInterface.callCount."+tempInterface.getId(),0L);
                    //初始或者重新从数据库同步
                    if(value.equals(0L)){
                        redisCache.addCount(RedisKey.COUNT+"thirdInterface.callCount."+tempInterface.getId(),tempInterface.getCallCount());
                        redisCache.addCount(RedisKey.COUNT+"thirdInterface.hitCount."+tempInterface.getId(),tempInterface.getHitCount());
                        redisCache.reduceCount(RedisKey.COUNT+"thirdInterface.balCount."+tempInterface.getId(),-tempInterface.getBalCount());
                        redisCache.deleteObject(RedisKey.COUNT+"thirdInterface.user."+tempInterface.getId());
                    }
                }else{
                    Long value =   redisCache.addCount(RedisKey.COUNT+SystemConst.INNER_VERSION+".callCount."+tempInterface.getId(),0L);
                    if(value.equals(0L)){
                        redisCache.addCount(RedisKey.COUNT+SystemConst.INNER_VERSION+".callCount",tempInterface.getCallCount());
                        redisCache.addCount(RedisKey.COUNT+SystemConst.INNER_VERSION+".hitCount",tempInterface.getHitCount());
                        redisCache.reduceCount(RedisKey.COUNT+SystemConst.INNER_VERSION+".balCount",-tempInterface.getBalCount());
                        redisCache.deleteObject(RedisKey.COUNT+SystemConst.INNER_VERSION+".user");
                    }
                }
            }
        }

    }

    /**
     * 初始第三方接口缓存
     */
    public void initThirdInterface(){
        redisCache.deleteObject(RedisKey.THIRD_INTERFACE);
        AzInterface azInterface=new AzInterface();
        azInterface.setStatus(SystemConst.Y);
        List<AzInterface> interfaceList=   interfaceService.selectAzInterfaceList(azInterface);
        if(interfaceList!=null && interfaceList.size()>0){
            List<AzInterface> thirdInterface=new ArrayList<AzInterface>();
            for(AzInterface tempInterface:interfaceList){
              String version =  tempInterface.getVersion();
              if(!SystemConst.INNER_VERSION.equals(version)){
                  thirdInterface.add(tempInterface);
              }
            }
            redisCache.setCacheList(RedisKey.THIRD_INTERFACE,thirdInterface);

        }
    }
    public void initPubWhitePhone(){
        AzPhoneWhite azPhoneBlack=new AzPhoneWhite();
        azPhoneBlack.setIsImport(SysConst.N);
        azPhoneBlack.setCreateUserId(SysConst.admin);
        PageHelper.startPage(1, 2000, "");
        List<AzPhoneWhite> phoneBlackList =  whitleService.selectAzPhoneWhiteList(azPhoneBlack);
        boolean imPort=true;
        while(imPort) {
            if (phoneBlackList != null && phoneBlackList.size() > 0) {
                List<Long> ids = new ArrayList<Long>();
                for (AzPhoneWhite entity : phoneBlackList) {
                    redisCache.setCacheMapValue(RedisKey.PUB_PHONEWHITE, entity.getPhone(), "1");
                    ids.add(entity.getId());
                }
                whitleService.updateImportByIds(ids);
                logger.info("白名单导入完成:" + ids.size());
            } else {
                imPort = false;
            }
        }
    }
    public void initPubBlackPhone(){
        AzPhoneBlack azPhoneBlack=new AzPhoneBlack();
        azPhoneBlack.setIsImport(SysConst.N);
       // azPhoneBlack.setCreateUserId(SysConst.admin);
        PageHelper.startPage(1, 2000, "");
       List<AzPhoneBlack> phoneBlackList =  blackService.selectAzPhoneBlackList(azPhoneBlack);
        boolean imPort=true;
        while(imPort) {
                if (phoneBlackList != null && phoneBlackList.size() > 0) {
                    List<Long> ids = new ArrayList<Long>();
                    for (AzPhoneBlack entity : phoneBlackList) {
                        redisCache.setCacheMapValue(RedisKey.PUB_PHONEBLACK, entity.getPhone(), "1");
                        ids.add(entity.getId());
                    }
                    blackService.updateImportByIds(ids);
                    logger.info("黑名单导入完成:" + ids.size());
                } else {
                    imPort = false;
                }
        }
    }

    public void initPriBlackPhone(){
        AzPhoneBlack azPhoneBlack=new AzPhoneBlack();
        azPhoneBlack.setIsImport(SysConst.N);
        Map<String,Object> paramsMap=new HashMap<String,Object>();
        paramsMap.put("neUserId",SysConst.admin);
        azPhoneBlack.setParams(paramsMap);
        PageHelper.startPage(1, 2000, "");
        List<AzPhoneBlack> phoneBlackList =  blackService.selectAzPhoneBlackList(azPhoneBlack);
        boolean imPort=true;
        while(imPort) {
            if (phoneBlackList != null && phoneBlackList.size() > 0) {
                List<Long> ids = new ArrayList<Long>();
                for (AzPhoneBlack entity : phoneBlackList) {
                    redisCache.setCacheMapValue(RedisKey.PRI_USER_PHONEBLACK+entity.getCreateUserId(), entity.getPhone(), "1");
                    ids.add(entity.getId());
                }
                blackService.updateImportByIds(ids);
                logger.info("黑名单导入完成:" + ids.size());
            } else {
                imPort = false;
            }
        }
    }
    public void initPriWhitePhone(){
        AzPhoneWhite azPhoneWhite=new AzPhoneWhite();
        azPhoneWhite.setIsImport(SysConst.N);
        Map<String,Object> paramsMap=new HashMap<String,Object>();
        paramsMap.put("neUserId",SysConst.admin);
        azPhoneWhite.setParams(paramsMap);
        PageHelper.startPage(1, 2000, "");
        List<AzPhoneWhite> phoneBlackList =  whitleService.selectAzPhoneWhiteList(azPhoneWhite);
        boolean imPort=true;
        while(imPort) {
            if (phoneBlackList != null && phoneBlackList.size() > 0) {
                List<Long> ids = new ArrayList<Long>();
                for (AzPhoneWhite entity : phoneBlackList) {
                    redisCache.setCacheMapValue(RedisKey.PRI_USER_PHONEWHITE+entity.getCreateUserId(), entity.getPhone(), "1");
                    ids.add(entity.getId());
                }
                whitleService.updateImportByIds(ids);
                logger.info("白名单导入完成:" + ids.size());
            } else {
                imPort = false;
            }
        }
    }
}
