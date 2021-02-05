package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
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
import com.ruoyi.system.domain.AzRule;
import com.ruoyi.system.service.IAzRuleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 拦截规则Controller
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@RestController
@RequestMapping("/system/rule")
public class AzRuleController extends BaseController
{
    @Autowired
    private IAzRuleService azRuleService;

    /**
     * 查询拦截规则列表
     */
    @PreAuthorize("@ss.hasPermi('system:rule:list')")
    @GetMapping("/list")
    public TableDataInfo list(AzRule azRule)
    {
        startPage();
        List<AzRule> list = azRuleService.selectAzRuleList(azRule);
        return getDataTable(list);
    }

    /**
     * 导出拦截规则列表
     */
    @PreAuthorize("@ss.hasPermi('system:rule:export')")
    @Log(title = "拦截规则", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AzRule azRule)
    {
        List<AzRule> list = azRuleService.selectAzRuleList(azRule);
        ExcelUtil<AzRule> util = new ExcelUtil<AzRule>(AzRule.class);
        return util.exportExcel(list, "rule");
    }

    /**
     * 获取拦截规则详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:rule:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(azRuleService.selectAzRuleById(id));
    }

    /**
     * 新增拦截规则
     */
    @PreAuthorize("@ss.hasPermi('system:rule:add')")
    @Log(title = "拦截规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AzRule azRule)
    {
        return toAjax(azRuleService.insertAzRule(azRule));
    }

    /**
     * 修改拦截规则
     */
    @PreAuthorize("@ss.hasPermi('system:rule:edit')")
    @Log(title = "拦截规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AzRule azRule)
    {
        return toAjax(azRuleService.updateAzRule(azRule));
    }

    /**
     * 删除拦截规则
     */
    @PreAuthorize("@ss.hasPermi('system:rule:remove')")
    @Log(title = "拦截规则", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(azRuleService.deleteAzRuleByIds(ids));
    }
}
