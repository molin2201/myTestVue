package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AzRuleMapper;
import com.ruoyi.system.domain.AzRule;
import com.ruoyi.system.service.IAzRuleService;

/**
 * 拦截规则Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@Service
public class AzRuleServiceImpl implements IAzRuleService 
{
    @Autowired
    private AzRuleMapper azRuleMapper;

    /**
     * 查询拦截规则
     * 
     * @param id 拦截规则ID
     * @return 拦截规则
     */
    @Override
    public AzRule selectAzRuleById(Long id)
    {
        return azRuleMapper.selectAzRuleById(id);
    }

    /**
     * 查询拦截规则列表
     * 
     * @param azRule 拦截规则
     * @return 拦截规则
     */
    @Override
    public List<AzRule> selectAzRuleList(AzRule azRule)
    {
        return azRuleMapper.selectAzRuleList(azRule);
    }

    /**
     * 新增拦截规则
     * 
     * @param azRule 拦截规则
     * @return 结果
     */
    @Override
    public int insertAzRule(AzRule azRule)
    {
        azRule.setCreateTime(DateUtils.getNowDate());
        return azRuleMapper.insertAzRule(azRule);
    }

    /**
     * 修改拦截规则
     * 
     * @param azRule 拦截规则
     * @return 结果
     */
    @Override
    public int updateAzRule(AzRule azRule)
    {
        return azRuleMapper.updateAzRule(azRule);
    }

    /**
     * 批量删除拦截规则
     * 
     * @param ids 需要删除的拦截规则ID
     * @return 结果
     */
    @Override
    public int deleteAzRuleByIds(Long[] ids)
    {
        return azRuleMapper.deleteAzRuleByIds(ids);
    }

    /**
     * 删除拦截规则信息
     * 
     * @param id 拦截规则ID
     * @return 结果
     */
    @Override
    public int deleteAzRuleById(Long id)
    {
        return azRuleMapper.deleteAzRuleById(id);
    }
}
