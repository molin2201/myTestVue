package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.AzPhoneWhite;
import org.apache.ibatis.annotations.Param;

/**
 * 白名单Mapper接口
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public interface AzPhoneWhiteMapper 
{

    public void updateImportByIds(@Param("list")List<Long> idsList);

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
     * 删除白名单
     * 
     * @param id 白名单ID
     * @return 结果
     */
    public int deleteAzPhoneWhiteById(Long id);

    /**
     * 批量删除白名单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAzPhoneWhiteByIds(Long[] ids);
}
