package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.AzInterface;

/**
 * 接口配置Service接口
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public interface IAzInterfaceService 
{
    /**
     * 查询接口配置
     * 
     * @param id 接口配置ID
     * @return 接口配置
     */
    public AzInterface selectAzInterfaceById(Long id);

    /**
     * 查询接口配置列表
     * 
     * @param azInterface 接口配置
     * @return 接口配置集合
     */
    public List<AzInterface> selectAzInterfaceList(AzInterface azInterface);

    /**
     * 新增接口配置
     * 
     * @param azInterface 接口配置
     * @return 结果
     */
    public int insertAzInterface(AzInterface azInterface);

    /**
     * 修改接口配置
     * 
     * @param azInterface 接口配置
     * @return 结果
     */
    public int updateAzInterface(AzInterface azInterface);

    /**
     * 批量删除接口配置
     * 
     * @param ids 需要删除的接口配置ID
     * @return 结果
     */
    public int deleteAzInterfaceByIds(Long[] ids);

    /**
     * 删除接口配置信息
     * 
     * @param id 接口配置ID
     * @return 结果
     */
    public int deleteAzInterfaceById(Long id);
}
