package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AzCityMapper;
import com.ruoyi.system.domain.AzCity;
import com.ruoyi.system.service.IAzCityService;

/**
 * 城市号码Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@Service
public class AzCityServiceImpl implements IAzCityService 
{
    @Autowired
    private AzCityMapper azCityMapper;

    /**
     * 查询城市号码
     * 
     * @param id 城市号码ID
     * @return 城市号码
     */
    @Override
    public AzCity selectAzCityById(Long id)
    {
        return azCityMapper.selectAzCityById(id);
    }

    /**
     * 查询城市号码列表
     * 
     * @param azCity 城市号码
     * @return 城市号码
     */
    @Override
    public List<AzCity> selectAzCityList(AzCity azCity)
    {
        return azCityMapper.selectAzCityList(azCity);
    }

    /**
     * 新增城市号码
     * 
     * @param azCity 城市号码
     * @return 结果
     */
    @Override
    public int insertAzCity(AzCity azCity)
    {
        return azCityMapper.insertAzCity(azCity);
    }

    /**
     * 修改城市号码
     * 
     * @param azCity 城市号码
     * @return 结果
     */
    @Override
    public int updateAzCity(AzCity azCity)
    {
        azCity.setUpdateTime(DateUtils.getNowDate());
        return azCityMapper.updateAzCity(azCity);
    }

    /**
     * 批量删除城市号码
     * 
     * @param ids 需要删除的城市号码ID
     * @return 结果
     */
    @Override
    public int deleteAzCityByIds(Long[] ids)
    {
        return azCityMapper.deleteAzCityByIds(ids);
    }

    /**
     * 删除城市号码信息
     * 
     * @param id 城市号码ID
     * @return 结果
     */
    @Override
    public int deleteAzCityById(Long id)
    {
        return azCityMapper.deleteAzCityById(id);
    }
}
