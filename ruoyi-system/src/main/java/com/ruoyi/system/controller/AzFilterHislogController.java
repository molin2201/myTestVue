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
import com.ruoyi.system.domain.AzFilterHislog;
import com.ruoyi.system.service.IAzFilterHislogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 拦截日志Controller
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@RestController
@RequestMapping("/system/hislog")
public class AzFilterHislogController extends BaseController
{
    @Autowired
    private IAzFilterHislogService azFilterHislogService;

    /**
     * 查询拦截日志列表
     */
    @PreAuthorize("@ss.hasPermi('system:hislog:list')")
    @GetMapping("/list")
    public TableDataInfo list(AzFilterHislog azFilterHislog)
    {
        startPage();
        List<AzFilterHislog> list = azFilterHislogService.selectAzFilterHislogList(azFilterHislog);
        return getDataTable(list);
    }

    /**
     * 导出拦截日志列表
     */
    @PreAuthorize("@ss.hasPermi('system:hislog:export')")
    @Log(title = "拦截日志", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AzFilterHislog azFilterHislog)
    {
        List<AzFilterHislog> list = azFilterHislogService.selectAzFilterHislogList(azFilterHislog);
        ExcelUtil<AzFilterHislog> util = new ExcelUtil<AzFilterHislog>(AzFilterHislog.class);
        return util.exportExcel(list, "hislog");
    }

    /**
     * 获取拦截日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:hislog:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(azFilterHislogService.selectAzFilterHislogById(id));
    }

    /**
     * 新增拦截日志
     */
    @PreAuthorize("@ss.hasPermi('system:hislog:add')")
    @Log(title = "拦截日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AzFilterHislog azFilterHislog)
    {
        return toAjax(azFilterHislogService.insertAzFilterHislog(azFilterHislog));
    }

    /**
     * 修改拦截日志
     */
    @PreAuthorize("@ss.hasPermi('system:hislog:edit')")
    @Log(title = "拦截日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AzFilterHislog azFilterHislog)
    {
        return toAjax(azFilterHislogService.updateAzFilterHislog(azFilterHislog));
    }

    /**
     * 删除拦截日志
     */
    @PreAuthorize("@ss.hasPermi('system:hislog:remove')")
    @Log(title = "拦截日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(azFilterHislogService.deleteAzFilterHislogByIds(ids));
    }
}
