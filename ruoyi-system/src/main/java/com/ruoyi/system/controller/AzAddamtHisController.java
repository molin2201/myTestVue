package com.ruoyi.system.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.AzAddamtHis;
import com.ruoyi.system.service.IAzAddamtHisService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 充值记录Controller
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@RestController
@RequestMapping("/system/his")
public class AzAddamtHisController extends BaseController
{
    @Autowired
    private IAzAddamtHisService azAddamtHisService;

    /**
     * 查询充值记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:his:list')")
    @GetMapping("/list")
    public TableDataInfo list(AzAddamtHis azAddamtHis)
    {
        startPage();
        List<AzAddamtHis> list = azAddamtHisService.selectAzAddamtHisList(azAddamtHis);
        return getDataTable(list);
    }

    /**
     * 导出充值记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:his:export')")
    @Log(title = "充值记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AzAddamtHis azAddamtHis)
    {
        List<AzAddamtHis> list = azAddamtHisService.selectAzAddamtHisList(azAddamtHis);
        ExcelUtil<AzAddamtHis> util = new ExcelUtil<AzAddamtHis>(AzAddamtHis.class);
        return util.exportExcel(list, "his");
    }

    /**
     * 获取充值记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:his:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(azAddamtHisService.selectAzAddamtHisById(id));
    }

    /**
     * 新增充值记录
     */
    @PreAuthorize("@ss.hasPermi('system:his:add')")
    @Log(title = "充值记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AzAddamtHis azAddamtHis)
    {
        return toAjax(azAddamtHisService.insertAzAddamtHis(azAddamtHis));
    }

    /**
     * 修改充值记录
     */
    @PreAuthorize("@ss.hasPermi('system:his:edit')")
    @Log(title = "充值记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AzAddamtHis azAddamtHis)
    {
        return toAjax(azAddamtHisService.updateAzAddamtHis(azAddamtHis));
    }

    /**
     * 删除充值记录
     */
    @PreAuthorize("@ss.hasPermi('system:his:remove')")
    @Log(title = "充值记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(azAddamtHisService.deleteAzAddamtHisByIds(ids));
    }
}
