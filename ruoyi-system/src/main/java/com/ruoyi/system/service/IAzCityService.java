package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.AzCity;

/**
 * 城市号码Service接口
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public interface IAzCityService 
{
    /**
     * 查询城市号码
     * 
     * @param id 城市号码ID
     * @return 城市号码
     */
    public AzCity selectAzCityById(Long id);

    /**
     * 查询城市号码列表
     * 
     * @param azCity 城市号码
     * @return 城市号码集合
     */
    public List<AzCity> selectAzCityList(AzCity azCity);

    /**
     * 新增城市号码
     * 
     * @param azCity 城市号码
     * @return 结果
     */
    public int insertAzCity(AzCity azCity);

    /**
     * 修改城市号码
     * 
     * @param azCity 城市号码
     * @return 结果
     */
    public int updateAzCity(AzCity azCity);

    /**
     * 批量删除城市号码
     * 
     * @param ids 需要删除的城市号码ID
     * @return 结果
     */
    public int deleteAzCityByIds(Long[] ids);

    /**
     * 删除城市号码信息
     * 
     * @param id 城市号码ID
     * @return 结果
     */
    public int deleteAzCityById(Long id);
}
