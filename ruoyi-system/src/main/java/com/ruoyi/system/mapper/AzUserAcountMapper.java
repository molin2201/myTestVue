package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.AzUserAcount;

/**
 * 用户账户Mapper接口
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public interface AzUserAcountMapper 
{
    /**
     * 查询用户账户
     * 
     * @param id 用户账户ID
     * @return 用户账户
     */
    public AzUserAcount selectAzUserAcountById(Long id);

    /**
     * 查询用户账户列表
     * 
     * @param azUserAcount 用户账户
     * @return 用户账户集合
     */
    public List<AzUserAcount> selectAzUserAcountList(AzUserAcount azUserAcount);

    /**
     * 新增用户账户
     * 
     * @param azUserAcount 用户账户
     * @return 结果
     */
    public int insertAzUserAcount(AzUserAcount azUserAcount);

    /**
     * 修改用户账户
     * 
     * @param azUserAcount 用户账户
     * @return 结果
     */
    public int updateAzUserAcount(AzUserAcount azUserAcount);

    /**
     * 删除用户账户
     * 
     * @param id 用户账户ID
     * @return 结果
     */
    public int deleteAzUserAcountById(Long id);

    /**
     * 批量删除用户账户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAzUserAcountByIds(Long[] ids);
}
