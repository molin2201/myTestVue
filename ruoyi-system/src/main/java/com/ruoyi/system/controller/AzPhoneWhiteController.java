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
import com.ruoyi.system.domain.AzPhoneWhite;
import com.ruoyi.system.service.IAzPhoneWhiteService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 白名单Controller
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@RestController
@RequestMapping("/system/white")
public class AzPhoneWhiteController extends BaseController
{
    @Autowired
    private IAzPhoneWhiteService azPhoneWhiteService;

    /**
     * 查询白名单列表
     */
    @PreAuthorize("@ss.hasPermi('system:white:list')")
    @GetMapping("/list")
    public TableDataInfo list(AzPhoneWhite azPhoneWhite)
    {
        startPage();
        List<AzPhoneWhite> list = azPhoneWhiteService.selectAzPhoneWhiteList(azPhoneWhite);
        return getDataTable(list);
    }

    /**
     * 导出白名单列表
     */
    @PreAuthorize("@ss.hasPermi('system:white:export')")
    @Log(title = "白名单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AzPhoneWhite azPhoneWhite)
    {
        List<AzPhoneWhite> list = azPhoneWhiteService.selectAzPhoneWhiteList(azPhoneWhite);
        ExcelUtil<AzPhoneWhite> util = new ExcelUtil<AzPhoneWhite>(AzPhoneWhite.class);
        return util.exportExcel(list, "white");
    }

    /**
     * 获取白名单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:white:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(azPhoneWhiteService.selectAzPhoneWhiteById(id));
    }

    /**
     * 新增白名单
     */
    @PreAuthorize("@ss.hasPermi('system:white:add')")
    @Log(title = "白名单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AzPhoneWhite azPhoneWhite)
    {
        return toAjax(azPhoneWhiteService.insertAzPhoneWhite(azPhoneWhite));
    }

    /**
     * 修改白名单
     */
    @PreAuthorize("@ss.hasPermi('system:white:edit')")
    @Log(title = "白名单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AzPhoneWhite azPhoneWhite)
    {
        return toAjax(azPhoneWhiteService.updateAzPhoneWhite(azPhoneWhite));
    }

    /**
     * 删除白名单
     */
    @PreAuthorize("@ss.hasPermi('system:white:remove')")
    @Log(title = "白名单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(azPhoneWhiteService.deleteAzPhoneWhiteByIds(ids));
    }
}
