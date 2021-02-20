package com.ruoyi.system.domain.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author molin
 */
public class AzRuleVo implements Serializable {

    private String userCode;
    private List<String> phonePattern;
    private List<String> areas;
    private List<String> companys;
    private List<String> phonePre;//手机前缀

    public List<String> getPhonePre() {
        return phonePre;
    }

    public void setPhonePre(List<String> phonePre) {
        this.phonePre = phonePre;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public List<String> getPhonePattern() {
        return phonePattern;
    }

    public void setPhonePattern(List<String> phonePattern) {
        this.phonePattern = phonePattern;
    }

    public List<String> getAreas() {
        return areas;
    }

    public void setAreas(List<String> areas) {
        this.areas = areas;
    }

    public List<String> getCompanys() {
        return companys;
    }

    public void setCompanys(List<String> companys) {
        this.companys = companys;
    }
}
