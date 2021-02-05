package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AzPhoneareaMapper;
import com.ruoyi.system.domain.AzPhonearea;
import com.ruoyi.system.service.IAzPhoneareaService;

/**
 * 手机地区Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@Service
public class AzPhoneareaServiceImpl implements IAzPhoneareaService 
{
    @Autowired
    private AzPhoneareaMapper azPhoneareaMapper;

    /**
     * 查询手机地区
     * 
     * @param id 手机地区ID
     * @return 手机地区
     */
    @Override
    public AzPhonearea selectAzPhoneareaById(Long id)
    {
        return azPhoneareaMapper.selectAzPhoneareaById(id);
    }

    /**
     * 查询手机地区列表
     * 
     * @param azPhonearea 手机地区
     * @return 手机地区
     */
    @Override
    public List<AzPhonearea> selectAzPhoneareaList(AzPhonearea azPhonearea)
    {
        return azPhoneareaMapper.selectAzPhoneareaList(azPhonearea);
    }

    /**
     * 新增手机地区
     * 
     * @param azPhonearea 手机地区
     * @return 结果
     */
    @Override
    public int insertAzPhonearea(AzPhonearea azPhonearea)
    {
        return azPhoneareaMapper.insertAzPhonearea(azPhonearea);
    }

    /**
     * 修改手机地区
     * 
     * @param azPhonearea 手机地区
     * @return 结果
     */
    @Override
    public int updateAzPhonearea(AzPhonearea azPhonearea)
    {
        azPhonearea.setUpdateTime(DateUtils.getNowDate());
        return azPhoneareaMapper.updateAzPhonearea(azPhonearea);
    }

    /**
     * 批量删除手机地区
     * 
     * @param ids 需要删除的手机地区ID
     * @return 结果
     */
    @Override
    public int deleteAzPhoneareaByIds(Long[] ids)
    {
        return azPhoneareaMapper.deleteAzPhoneareaByIds(ids);
    }

    /**
     * 删除手机地区信息
     * 
     * @param id 手机地区ID
     * @return 结果
     */
    @Override
    public int deleteAzPhoneareaById(Long id)
    {
        return azPhoneareaMapper.deleteAzPhoneareaById(id);
    }
}
