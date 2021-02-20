package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.AzPhoneBlack;
import org.apache.ibatis.annotations.Param;

/**
 * 黑名单Mapper接口
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public interface AzPhoneBlackMapper 
{
    /**
     * 查询黑名单
     * 
     * @param id 黑名单ID
     * @return 黑名单
     */
    public AzPhoneBlack selectAzPhoneBlackById(Long id);

    /**
     * 查询黑名单列表
     * 
     * @param azPhoneBlack 黑名单
     * @return 黑名单集合
     */
    public List<AzPhoneBlack> selectAzPhoneBlackList(AzPhoneBlack azPhoneBlack);

    /**
     * 新增黑名单
     * 
     * @param azPhoneBlack 黑名单
     * @return 结果
     */
    public int insertAzPhoneBlack(AzPhoneBlack azPhoneBlack);

    /**
     * 修改黑名单
     * 
     * @param azPhoneBlack 黑名单
     * @return 结果
     */
    public int updateAzPhoneBlack(AzPhoneBlack azPhoneBlack);

    /**
     * 删除黑名单
     * 
     * @param id 黑名单ID
     * @return 结果
     */
    public int deleteAzPhoneBlackById(Long id);

    /**
     * 批量删除黑名单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAzPhoneBlackByIds(Long[] ids);

    public void updateImportByIds(@Param("list")List<Long> idsList);

}
