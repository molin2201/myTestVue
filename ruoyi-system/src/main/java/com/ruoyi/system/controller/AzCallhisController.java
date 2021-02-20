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
import com.ruoyi.system.domain.AzCallhis;
import com.ruoyi.system.service.IAzCallhisService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 外呼记录Controller
 * 
 * @author ruoyi
 * @date 2021-02-10
 */
@RestController
@RequestMapping("/system/callhis")
public class AzCallhisController extends BaseController
{
    @Autowired
    private IAzCallhisService azCallhisService;

    /**
     * 查询外呼记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:callhis:list')")
    @GetMapping("/list")
    public TableDataInfo list(AzCallhis azCallhis)
    {
        startPage();
        List<AzCallhis> list = azCallhisService.selectAzCallhisList(azCallhis);
        return getDataTable(list);
    }

    /**
     * 导出外呼记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:callhis:export')")
    @Log(title = "外呼记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AzCallhis azCallhis)
    {
        List<AzCallhis> list = azCallhisService.selectAzCallhisList(azCallhis);
        ExcelUtil<AzCallhis> util = new ExcelUtil<AzCallhis>(AzCallhis.class);
        return util.exportExcel(list, "callhis");
    }

    /**
     * 获取外呼记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:callhis:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(azCallhisService.selectAzCallhisById(id));
    }

    /**
     * 新增外呼记录
     */
    @PreAuthorize("@ss.hasPermi('system:callhis:add')")
    @Log(title = "外呼记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AzCallhis azCallhis)
    {
        return toAjax(azCallhisService.insertAzCallhis(azCallhis));
    }

    /**
     * 修改外呼记录
     */
    @PreAuthorize("@ss.hasPermi('system:callhis:edit')")
    @Log(title = "外呼记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AzCallhis azCallhis)
    {
        return toAjax(azCallhisService.updateAzCallhis(azCallhis));
    }

    /**
     * 删除外呼记录
     */
    @PreAuthorize("@ss.hasPermi('system:callhis:remove')")
    @Log(title = "外呼记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(azCallhisService.deleteAzCallhisByIds(ids));
    }
}
