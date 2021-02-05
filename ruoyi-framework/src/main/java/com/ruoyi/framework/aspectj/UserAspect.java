package com.ruoyi.framework.aspectj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BusinessStatus;
import com.ruoyi.common.enums.HttpMethod;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.JsonUtil;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.SysOperLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * 操作日志记录处理
 * 
 * @author ruoyi
 */
@Aspect
@Component
public class UserAspect
{
    private static final Logger log = LoggerFactory.getLogger(UserAspect.class);
    private static String page=".domain.Az";

    // 外部接口请求调用
    @Pointcut("execution(public * com.ruoyi.system..*.add*(..))")
    public void inApiLog(){

    }


    // 外部接口请求调用
    @Pointcut("execution(public * com.ruoyi.system..*.edit*(..))")
    public void editApiLog(){

    }

    @Before(value = "inApiLog()")
    public void beforeEditApiLog(JoinPoint joinPoint) {
        try {
            LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());
            for (Object object : joinPoint.getArgs()) {
                if (object instanceof MultipartFile || object instanceof HttpServletRequest
                        || object instanceof HttpServletResponse) {
                    continue;
                }
                if(object!=null&& object.getClass().getName().contains(page)){
                    invokeMethod(object,"setUpdateUserId",loginUser.getUsername());
                    invokeMethod(object,"setUpdateTime",DateUtils.getTime());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void invokeMethod(Object object,String fieldName,String userName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try{
                Method m = object.getClass().getMethod(fieldName,String.class);
                m.invoke(object, userName);
        }catch (Exception ex){
        }
    }

    @Before(value = "inApiLog()")
    public void beforeAdduser(JoinPoint joinPoint) {
        try {
            LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());
            for (Object object : joinPoint.getArgs()) {
                if (object instanceof MultipartFile || object instanceof HttpServletRequest
                        || object instanceof HttpServletResponse) {
                    continue;
                }
                if(object!=null&& object.getClass().getName().contains(page)){
                    invokeMethod(object,"setUserCode",loginUser.getUsername());
                    invokeMethod(object,"setCreateUserId",loginUser.getUsername());
                    invokeMethod(object,"setUpdateUserId",loginUser.getUsername());
                    invokeMethod(object,"setCreateTime", DateUtils.getTime());
                    invokeMethod(object,"setUpdateTime",DateUtils.getTime());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取目标方法
     * @param proxyObject
     * @param methodStr
     * @return
     */
    private Method getMethod(Class proxyObject, String methodStr) {
        Method[] methods = proxyObject.getMethods();

        for(Method method : methods) {
            if(method.getName().equalsIgnoreCase(methodStr)) {
                return method;
            }
        }

        return null;
    }
}
