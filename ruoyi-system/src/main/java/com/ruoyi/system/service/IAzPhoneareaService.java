package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.AzPhonearea;

/**
 * 手机地区Service接口
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public interface IAzPhoneareaService 
{
    /**
     * 查询手机地区
     * 
     * @param id 手机地区ID
     * @return 手机地区
     */
    public AzPhonearea selectAzPhoneareaById(Long id);

    /**
     * 查询手机地区列表
     * 
     * @param azPhonearea 手机地区
     * @return 手机地区集合
     */
    public List<AzPhonearea> selectAzPhoneareaList(AzPhonearea azPhonearea);

    /**
     * 新增手机地区
     * 
     * @param azPhonearea 手机地区
     * @return 结果
     */
    public int insertAzPhonearea(AzPhonearea azPhonearea);

    /**
     * 修改手机地区
     * 
     * @param azPhonearea 手机地区
     * @return 结果
     */
    public int updateAzPhonearea(AzPhonearea azPhonearea);

    /**
     * 批量删除手机地区
     * 
     * @param ids 需要删除的手机地区ID
     * @return 结果
     */
    public int deleteAzPhoneareaByIds(Long[] ids);

    /**
     * 删除手机地区信息
     * 
     * @param id 手机地区ID
     * @return 结果
     */
    public int deleteAzPhoneareaById(Long id);
}
