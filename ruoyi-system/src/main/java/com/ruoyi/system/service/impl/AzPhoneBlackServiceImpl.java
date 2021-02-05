package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AzPhoneBlackMapper;
import com.ruoyi.system.domain.AzPhoneBlack;
import com.ruoyi.system.service.IAzPhoneBlackService;

/**
 * 黑名单Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@Service
public class AzPhoneBlackServiceImpl implements IAzPhoneBlackService 
{
    @Autowired
    private AzPhoneBlackMapper azPhoneBlackMapper;

    /**
     * 查询黑名单
     * 
     * @param id 黑名单ID
     * @return 黑名单
     */
    @Override
    public AzPhoneBlack selectAzPhoneBlackById(Long id)
    {
        return azPhoneBlackMapper.selectAzPhoneBlackById(id);
    }

    /**
     * 查询黑名单列表
     * 
     * @param azPhoneBlack 黑名单
     * @return 黑名单
     */
    @Override
    public List<AzPhoneBlack> selectAzPhoneBlackList(AzPhoneBlack azPhoneBlack)
    {
        return azPhoneBlackMapper.selectAzPhoneBlackList(azPhoneBlack);
    }

    /**
     * 新增黑名单
     * 
     * @param azPhoneBlack 黑名单
     * @return 结果
     */
    @Override
    public int insertAzPhoneBlack(AzPhoneBlack azPhoneBlack)
    {
        azPhoneBlack.setCreateTime(DateUtils.getNowDate());
        return azPhoneBlackMapper.insertAzPhoneBlack(azPhoneBlack);
    }

    /**
     * 修改黑名单
     * 
     * @param azPhoneBlack 黑名单
     * @return 结果
     */
    @Override
    public int updateAzPhoneBlack(AzPhoneBlack azPhoneBlack)
    {
        return azPhoneBlackMapper.updateAzPhoneBlack(azPhoneBlack);
    }

    /**
     * 批量删除黑名单
     * 
     * @param ids 需要删除的黑名单ID
     * @return 结果
     */
    @Override
    public int deleteAzPhoneBlackByIds(Long[] ids)
    {
        return azPhoneBlackMapper.deleteAzPhoneBlackByIds(ids);
    }

    /**
     * 删除黑名单信息
     * 
     * @param id 黑名单ID
     * @return 结果
     */
    @Override
    public int deleteAzPhoneBlackById(Long id)
    {
        return azPhoneBlackMapper.deleteAzPhoneBlackById(id);
    }
}
