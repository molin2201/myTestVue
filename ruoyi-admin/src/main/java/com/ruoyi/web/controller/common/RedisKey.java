package com.ruoyi.web.controller.common;

public class RedisKey {

    public static final String PUB_PHONEBLACK = "PUB.p";//黑名单公共库
    public static final String PUB_PHONEWHITE = "PUB.w";//白名单公共库
    public static final String THIRD_INTERFACE= "THIRD.interface";//第三方黑名单接口

    public static final String SYS_CONFIG= "yz.sys.config";//第三方黑名单接口
    public static final String RULE = "rule";//规则缓存

    public static final String PRI_CACHE_PHONEBLACK = "PRI.C.";//私有缓存库
    public static final String PRI_USER_PHONEBLACK = "PRI.P.";//私有公共库
    public static final String  PRI_USER_PHONEWHITE = "PRI.w.";//白名单公共库

    public  static final String TOPIC_ERROR="topic.error";
    public static final String COUNT = "count.";//规则缓存


}
