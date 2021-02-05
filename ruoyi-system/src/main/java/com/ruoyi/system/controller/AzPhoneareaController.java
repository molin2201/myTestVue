package com.ruoyi.system.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.AzPhonearea;
import com.ruoyi.system.service.IAzPhoneareaService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 手机地区Controller
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@RestController
@RequestMapping("/system/phonearea")
public class AzPhoneareaController extends BaseController
{
    @Autowired
    private IAzPhoneareaService azPhoneareaService;

    /**
     * 查询手机地区列表
     */
    @PreAuthorize("@ss.hasPermi('system:phonearea:list')")
    @GetMapping("/list")
    public TableDataInfo list(AzPhonearea azPhonearea)
    {
        startPage();
        List<AzPhonearea> list = azPhoneareaService.selectAzPhoneareaList(azPhonearea);
        return getDataTable(list);
    }

    /**
     * 导出手机地区列表
     */
    @PreAuthorize("@ss.hasPermi('system:phonearea:export')")
    @Log(title = "手机地区", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AzPhonearea azPhonearea)
    {
        List<AzPhonearea> list = azPhoneareaService.selectAzPhoneareaList(azPhonearea);
        ExcelUtil<AzPhonearea> util = new ExcelUtil<AzPhonearea>(AzPhonearea.class);
        return util.exportExcel(list, "phonearea");
    }

    /**
     * 获取手机地区详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:phonearea:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(azPhoneareaService.selectAzPhoneareaById(id));
    }

    /**
     * 新增手机地区
     */
    @PreAuthorize("@ss.hasPermi('system:phonearea:add')")
    @Log(title = "手机地区", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AzPhonearea azPhonearea)
    {
        System.out.println("xxxxxxxxxxxxx"+azPhonearea.getUpdateUserId());
        return toAjax(azPhoneareaService.insertAzPhonearea(azPhonearea));
    }

    /**
     * 修改手机地区
     */
    @PreAuthorize("@ss.hasPermi('system:phonearea:edit')")
    @Log(title = "手机地区", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AzPhonearea azPhonearea)
    {
        return toAjax(azPhoneareaService.updateAzPhonearea(azPhonearea));
    }

    /**
     * 删除手机地区
     */
    @PreAuthorize("@ss.hasPermi('system:phonearea:remove')")
    @Log(title = "手机地区", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(azPhoneareaService.deleteAzPhoneareaByIds(ids));
    }
}
