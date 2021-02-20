package com.ruoyi.web.controller.black;


import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.JsonUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.common.ConfigKey;
import com.ruoyi.system.common.SystemConst;
import com.ruoyi.system.domain.AzInterface;
import com.ruoyi.system.domain.vo.AzRuleVo;
import com.ruoyi.web.controller.common.RedisKey;
import com.ruoyi.web.core.config.SysConfigBiz;
import com.ruoyi.web.service.CountBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sun.security.krb5.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/zl/black/*")
public class InController {
    private static final Logger logger = LoggerFactory.getLogger(InController.class);
    @Autowired
    public RedisCache redisCache;
    @Autowired
    public SysConfigBiz sysConfigBiz;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CountBiz countBiz;
    /**
     * 保存黑名单
     * @param request
     * @return
     */
    @PostMapping("/blackInfo/{appId}/{userCode}")
    @ResponseBody
    public String blackInfo(HttpServletRequest request, @PathVariable String appId,@PathVariable String userCode) {
        BlackResp blackResp=new BlackResp();
        try {
            String req =  getRequestPostStr(request);
            logger.debug("黑名单查询参数："+req);
            String curDate= DateUtils.getDate();
            countBiz.interfaceCount(userCode,curDate);
            BlackReq blackReq = getReqParam(req);
            blackResp.setCallId(blackReq.getCallId());
            blackReq= checkPhone(blackReq);//截取前缀
            String key=buildCacheKey(blackReq.getCallee(),userCode);
            boolean isHist=false;
            if(!checkIsWhite(blackReq.getCallee(),userCode)){
                String phone =redisCache.getCacheObject(key);//缓存是否存在
                if(StringUtils.isNotEmpty(phone)){
                    isHist =true;
                    countBiz.interfaceHitCount(userCode,curDate);
                }else if (checkIsBlack(blackReq.getCallee(),userCode)) {//是否黑名单
                    isHist =true;
                    countBiz.interfaceHitCount(userCode,curDate);
                }else if(ruleCheck( blackReq.getCallee(), userCode)){//用户拦截规则
                    isHist =true;
                } else if (checkAllthird(userCode,curDate,blackReq)) {//第三方黑名单服务
                    isHist =true;
                }else{
                    redisCache.setCacheObject(key,"1",sysConfigBiz.getIntConfig(ConfigKey.PRI_PHONE_EXPIRETIMEMIN,2880), TimeUnit.MINUTES);
                }
            }
            if(isHist){
                blackResp.setForbid(1);
                blackResp.setTransactionId(UUID.randomUUID().toString().replace("-",""));
            }

        }catch(Exception ex){
            logger.error("黑名单查询异常",ex);
        }
        String ret = JsonUtil.obj2json(blackResp);
        logger.info("黑名单查询返回参数："+ret);
        return ret;
    }

    private boolean ruleCheck(String phone, String user){
        AzRuleVo azRuleVo = redisCache.getCacheMapValue(RedisKey.RULE,user);
        boolean isBlack=false;
        if(azRuleVo!=null){
            //校验地区、运营商前缀
          List<String> phonePres = azRuleVo.getPhonePre();
          for(int i=0;phonePres!=null&&i<phonePres.size();i++){
              if(phone.startsWith(phonePres.get(i))){
                  return true;
              }
          }
            //靓号
            List<String> phonePatterns = azRuleVo.getPhonePattern();
            for(int i=0;phonePatterns!=null&&i<phonePatterns.size();i++){
                if(StringUtils.checkByRule(phone,phonePres.get(i))){
                    return true;
                }
            }
        }
        return isBlack;
    }


    private String buildCacheKey(String phone, String user){
        if(StringUtils.isEmpty(user)){
            user="pub";
        }
        StringBuffer sb=new StringBuffer();
        sb.append(RedisKey.PRI_CACHE_PHONEBLACK).append(user).append(".").append(phone);
        return sb.toString();
    }
    public boolean checkAllthird(String useCode,String curDate,BlackReq req){

        List<AzInterface> thirdInterface =  redisCache.getCacheList(RedisKey.THIRD_INTERFACE);
        if(thirdInterface!=null && thirdInterface.size()>0){
            for(AzInterface azInterface:thirdInterface){
                countBiz.thirdInterfaceCount(useCode,azInterface.getId().toString(),curDate);
                if(thirdCheck(azInterface.getAddress(),req)){
                    countBiz.thirdInterfaceHitCount(useCode,azInterface.getId().toString(),curDate);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean thirdCheck(String url,BlackReq req){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        String content=JsonUtil.obj2json(req);
        HttpEntity<String> request = new HttpEntity<>(content, headers);
        try{
            ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
            if(response!=null && StringUtils.isNotEmpty(response.getBody())&&response.getBody().contains("forbid")){
                logger.info("黑名单查询第三方返回："+response.getBody());
                BlackResp resp=JsonUtil.objectMapper.readValue(response.getBody(),BlackResp.class);
                if(resp.getForbid()!=null){
                    redisCache.setCacheMapValue(RedisKey.PUB_PHONEBLACK,req.getCallee(),"1");
                    return true;
                }
            }
        }catch(Exception ex){

            logger.error("黑名单查询第三方异常"+url+","+ex.getMessage());
        }
        return false;
    }
    private  BlackReq checkPhone(BlackReq blackReq){
        if(blackReq!=null && blackReq.getCallee()!=null && blackReq.getCallee().length()>11){
            String phone=blackReq.getCallee();
            phone =phone.substring(phone.length()-11,phone.length());
            blackReq.setCallee(phone);
        }
        return blackReq;
    }


    /**
     * 保存黑名单
     * @param blackReq
     * @return
     */
    @RequestMapping(value ="saveBlackInfo", method = RequestMethod.POST)
    public String saveBlackInfo(@RequestBody BlackReq blackReq, HttpServletResponse response) {
              BlackResp blackResp=new BlackResp();
            try {

            }catch(Exception ex){
                logger.error("黑名单查询异常",ex);
            }
            String ret = JsonUtil.obj2json(blackResp);
             logger.info("黑名单查询返回参数："+ret);
            return  ret;
    }

    private BlackReq getReqParam( String req) throws IOException {

            BlackReq blackReq=null;
            if(req.contains("&")){
                StringBuffer sb=new StringBuffer();
                req = req.replace("=","\":\"").replace("&","\",\"");
                sb.append("{\"").append(req).append("\"}");
                blackReq=JsonUtil.objectMapper.readValue(sb.toString(),BlackReq.class);
            }else{
                blackReq=JsonUtil.objectMapper.readValue(req,BlackReq.class);
                if(blackReq==null ||StringUtils.isEmpty(blackReq.getCallee())){
                    BlackReqAi blackReqAi=JsonUtil.objectMapper.readValue(req,BlackReqAi.class);
                    blackReq =blackReqAi.getRewtiteE164Req();
                }
            }
            return blackReq;
        }

    /***
     * Compatible with GET and POST
     *
     * @param request
     * @return : <code>byte[]</code>
     * @throws IOException
     */
    public static byte[] getRequestQuery(HttpServletRequest request)
            throws IOException {
        String submitMehtod = request.getMethod();
        String queryString = null;

        if (submitMehtod.equals("GET")) {// GET
            queryString = request.getQueryString();
            String charEncoding = request.getCharacterEncoding();// charset
            if (charEncoding == null) {
                charEncoding = "UTF-8";
            }
            return queryString.getBytes(charEncoding);
        } else {// POST
            return getRequestPostBytes(request);
        }
    }

    /***
     * Get request query string, form method : post
     *
     * @param request
     * @return byte[]
     * @throws IOException
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }
    /***
     * Get request query string, form method : post
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

    /**
     * 是否白名单
     * @param phone
     * @param userCode
     * @return
     */
    private boolean checkIsWhite(String phone,String userCode){
        boolean isExist=    redisCache.cacheMapExists(RedisKey.PUB_PHONEWHITE,  phone);
        if(!isExist){
            isExist=   redisCache.cacheMapExists(RedisKey.PRI_USER_PHONEWHITE+userCode,  phone);
        }
        return isExist;
    }

    /**
     * 是否黑名单
     * @param phone
     * @param userCode
     * @return
     */
    private boolean checkIsBlack(String phone,String userCode){
        boolean isExist=    redisCache.cacheMapExists(RedisKey.PUB_PHONEBLACK,  phone);
        /*
        if(!isExist){
            isExist=   redisCache.cacheMapExists(RedisKey.PRI_USER_PHONEBLACK+userCode,  phone);
        }
         */
        return isExist;
    }
}
