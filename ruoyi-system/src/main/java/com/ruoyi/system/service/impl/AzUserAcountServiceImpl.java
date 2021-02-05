package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AzUserAcountMapper;
import com.ruoyi.system.domain.AzUserAcount;
import com.ruoyi.system.service.IAzUserAcountService;

/**
 * 用户账户Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@Service
public class AzUserAcountServiceImpl implements IAzUserAcountService 
{
    @Autowired
    private AzUserAcountMapper azUserAcountMapper;

    /**
     * 查询用户账户
     * 
     * @param id 用户账户ID
     * @return 用户账户
     */
    @Override
    public AzUserAcount selectAzUserAcountById(Long id)
    {
        return azUserAcountMapper.selectAzUserAcountById(id);
    }

    /**
     * 查询用户账户列表
     * 
     * @param azUserAcount 用户账户
     * @return 用户账户
     */
    @Override
    public List<AzUserAcount> selectAzUserAcountList(AzUserAcount azUserAcount)
    {
        return azUserAcountMapper.selectAzUserAcountList(azUserAcount);
    }

    /**
     * 新增用户账户
     * 
     * @param azUserAcount 用户账户
     * @return 结果
     */
    @Override
    public int insertAzUserAcount(AzUserAcount azUserAcount)
    {
        return azUserAcountMapper.insertAzUserAcount(azUserAcount);
    }

    /**
     * 修改用户账户
     * 
     * @param azUserAcount 用户账户
     * @return 结果
     */
    @Override
    public int updateAzUserAcount(AzUserAcount azUserAcount)
    {
        azUserAcount.setUpdateTime(DateUtils.getNowDate());
        return azUserAcountMapper.updateAzUserAcount(azUserAcount);
    }

    /**
     * 批量删除用户账户
     * 
     * @param ids 需要删除的用户账户ID
     * @return 结果
     */
    @Override
    public int deleteAzUserAcountByIds(Long[] ids)
    {
        return azUserAcountMapper.deleteAzUserAcountByIds(ids);
    }

    /**
     * 删除用户账户信息
     * 
     * @param id 用户账户ID
     * @return 结果
     */
    @Override
    public int deleteAzUserAcountById(Long id)
    {
        return azUserAcountMapper.deleteAzUserAcountById(id);
    }
}
