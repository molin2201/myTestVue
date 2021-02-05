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
import com.ruoyi.system.domain.AzExchangeRate;
import com.ruoyi.system.service.IAzExchangeRateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 费率管理Controller
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@RestController
@RequestMapping("/system/rate")
public class AzExchangeRateController extends BaseController
{
    @Autowired
    private IAzExchangeRateService azExchangeRateService;

    /**
     * 查询费率管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:rate:list')")
    @GetMapping("/list")
    public TableDataInfo list(AzExchangeRate azExchangeRate)
    {
        startPage();
        List<AzExchangeRate> list = azExchangeRateService.selectAzExchangeRateList(azExchangeRate);
        return getDataTable(list);
    }

    /**
     * 导出费率管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:rate:export')")
    @Log(title = "费率管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AzExchangeRate azExchangeRate)
    {
        List<AzExchangeRate> list = azExchangeRateService.selectAzExchangeRateList(azExchangeRate);
        ExcelUtil<AzExchangeRate> util = new ExcelUtil<AzExchangeRate>(AzExchangeRate.class);
        return util.exportExcel(list, "rate");
    }

    /**
     * 获取费率管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:rate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(azExchangeRateService.selectAzExchangeRateById(id));
    }

    /**
     * 新增费率管理
     */
    @PreAuthorize("@ss.hasPermi('system:rate:add')")
    @Log(title = "费率管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AzExchangeRate azExchangeRate)
    {
        return toAjax(azExchangeRateService.insertAzExchangeRate(azExchangeRate));
    }

    /**
     * 修改费率管理
     */
    @PreAuthorize("@ss.hasPermi('system:rate:edit')")
    @Log(title = "费率管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AzExchangeRate azExchangeRate)
    {
        return toAjax(azExchangeRateService.updateAzExchangeRate(azExchangeRate));
    }

    /**
     * 删除费率管理
     */
    @PreAuthorize("@ss.hasPermi('system:rate:remove')")
    @Log(title = "费率管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(azExchangeRateService.deleteAzExchangeRateByIds(ids));
    }
}
