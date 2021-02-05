package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.AzRule;

/**
 * 拦截规则Service接口
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public interface IAzRuleService 
{
    /**
     * 查询拦截规则
     * 
     * @param id 拦截规则ID
     * @return 拦截规则
     */
    public AzRule selectAzRuleById(Long id);

    /**
     * 查询拦截规则列表
     * 
     * @param azRule 拦截规则
     * @return 拦截规则集合
     */
    public List<AzRule> selectAzRuleList(AzRule azRule);

    /**
     * 新增拦截规则
     * 
     * @param azRule 拦截规则
     * @return 结果
     */
    public int insertAzRule(AzRule azRule);

    /**
     * 修改拦截规则
     * 
     * @param azRule 拦截规则
     * @return 结果
     */
    public int updateAzRule(AzRule azRule);

    /**
     * 批量删除拦截规则
     * 
     * @param ids 需要删除的拦截规则ID
     * @return 结果
     */
    public int deleteAzRuleByIds(Long[] ids);

    /**
     * 删除拦截规则信息
     * 
     * @param id 拦截规则ID
     * @return 结果
     */
    public int deleteAzRuleById(Long id);
}
