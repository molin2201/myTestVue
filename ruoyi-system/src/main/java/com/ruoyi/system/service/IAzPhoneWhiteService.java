package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.AzPhoneWhite;

/**
 * 白名单Service接口
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public interface IAzPhoneWhiteService 
{
    /**
     * 查询白名单
     * 
     * @param id 白名单ID
     * @return 白名单
     */
    public AzPhoneWhite selectAzPhoneWhiteById(Long id);

    /**
     * 查询白名单列表
     * 
     * @param azPhoneWhite 白名单
     * @return 白名单集合
     */
    public List<AzPhoneWhite> selectAzPhoneWhiteList(AzPhoneWhite azPhoneWhite);

    /**
     * 新增白名单
     * 
     * @param azPhoneWhite 白名单
     * @return 结果
     */
    public int insertAzPhoneWhite(AzPhoneWhite azPhoneWhite);

    /**
     * 修改白名单
     * 
     * @param azPhoneWhite 白名单
     * @return 结果
     */
    public int updateAzPhoneWhite(AzPhoneWhite azPhoneWhite);

    /**
     * 批量删除白名单
     * 
     * @param ids 需要删除的白名单ID
     * @return 结果
     */
    public int deleteAzPhoneWhiteByIds(Long[] ids);

    /**
     * 删除白名单信息
     * 
     * @param id 白名单ID
     * @return 结果
     */
    public int deleteAzPhoneWhiteById(Long id);
}
