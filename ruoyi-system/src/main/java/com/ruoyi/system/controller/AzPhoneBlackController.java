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
import com.ruoyi.system.domain.AzPhoneBlack;
import com.ruoyi.system.service.IAzPhoneBlackService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 黑名单Controller
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@RestController
@RequestMapping("/system/black")
public class AzPhoneBlackController extends BaseController
{
    @Autowired
    private IAzPhoneBlackService azPhoneBlackService;

    /**
     * 查询黑名单列表
     */
    @PreAuthorize("@ss.hasPermi('system:black:list')")
    @GetMapping("/list")
    public TableDataInfo list(AzPhoneBlack azPhoneBlack)
    {
        startPage();
        List<AzPhoneBlack> list = azPhoneBlackService.selectAzPhoneBlackList(azPhoneBlack);
        return getDataTable(list);
    }

    /**
     * 导出黑名单列表
     */
    @PreAuthorize("@ss.hasPermi('system:black:export')")
    @Log(title = "黑名单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AzPhoneBlack azPhoneBlack)
    {
        List<AzPhoneBlack> list = azPhoneBlackService.selectAzPhoneBlackList(azPhoneBlack);
        ExcelUtil<AzPhoneBlack> util = new ExcelUtil<AzPhoneBlack>(AzPhoneBlack.class);
        return util.exportExcel(list, "black");
    }

    /**
     * 获取黑名单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:black:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(azPhoneBlackService.selectAzPhoneBlackById(id));
    }

    /**
     * 新增黑名单
     */
    @PreAuthorize("@ss.hasPermi('system:black:add')")
    @Log(title = "黑名单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AzPhoneBlack azPhoneBlack)
    {
        return toAjax(azPhoneBlackService.insertAzPhoneBlack(azPhoneBlack));
    }

    /**
     * 修改黑名单
     */
    @PreAuthorize("@ss.hasPermi('system:black:edit')")
    @Log(title = "黑名单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AzPhoneBlack azPhoneBlack)
    {
        return toAjax(azPhoneBlackService.updateAzPhoneBlack(azPhoneBlack));
    }

    /**
     * 删除黑名单
     */
    @PreAuthorize("@ss.hasPermi('system:black:remove')")
    @Log(title = "黑名单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(azPhoneBlackService.deleteAzPhoneBlackByIds(ids));
    }
}
