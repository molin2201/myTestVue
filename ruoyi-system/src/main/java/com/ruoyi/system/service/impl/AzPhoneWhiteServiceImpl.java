package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AzPhoneWhiteMapper;
import com.ruoyi.system.domain.AzPhoneWhite;
import com.ruoyi.system.service.IAzPhoneWhiteService;

/**
 * 白名单Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@Service
public class AzPhoneWhiteServiceImpl implements IAzPhoneWhiteService 
{
    @Autowired
    private AzPhoneWhiteMapper azPhoneWhiteMapper;

    @Override
    public void updateImportByIds(List<Long> idsList){
        azPhoneWhiteMapper.updateImportByIds(idsList);
    }
    /**
     * 查询白名单
     * 
     * @param id 白名单ID
     * @return 白名单
     */
    @Override
    public AzPhoneWhite selectAzPhoneWhiteById(Long id)
    {
        return azPhoneWhiteMapper.selectAzPhoneWhiteById(id);
    }

    /**
     * 查询白名单列表
     * 
     * @param azPhoneWhite 白名单
     * @return 白名单
     */
    @Override
    public List<AzPhoneWhite> selectAzPhoneWhiteList(AzPhoneWhite azPhoneWhite)
    {
        return azPhoneWhiteMapper.selectAzPhoneWhiteList(azPhoneWhite);
    }

    /**
     * 新增白名单
     * 
     * @param azPhoneWhite 白名单
     * @return 结果
     */
    @Override
    public int insertAzPhoneWhite(AzPhoneWhite azPhoneWhite)
    {
        azPhoneWhite.setCreateTime(DateUtils.getNowDate());
        return azPhoneWhiteMapper.insertAzPhoneWhite(azPhoneWhite);
    }

    /**
     * 修改白名单
     * 
     * @param azPhoneWhite 白名单
     * @return 结果
     */
    @Override
    public int updateAzPhoneWhite(AzPhoneWhite azPhoneWhite)
    {
        return azPhoneWhiteMapper.updateAzPhoneWhite(azPhoneWhite);
    }

    /**
     * 批量删除白名单
     * 
     * @param ids 需要删除的白名单ID
     * @return 结果
     */
    @Override
    public int deleteAzPhoneWhiteByIds(Long[] ids)
    {
        return azPhoneWhiteMapper.deleteAzPhoneWhiteByIds(ids);
    }

    /**
     * 删除白名单信息
     * 
     * @param id 白名单ID
     * @return 结果
     */
    @Override
    public int deleteAzPhoneWhiteById(Long id)
    {
        return azPhoneWhiteMapper.deleteAzPhoneWhiteById(id);
    }
}
