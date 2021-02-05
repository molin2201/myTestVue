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
import com.ruoyi.system.domain.AzUserAcount;
import com.ruoyi.system.service.IAzUserAcountService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户账户Controller
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@RestController
@RequestMapping("/system/acount")
public class AzUserAcountController extends BaseController
{
    @Autowired
    private IAzUserAcountService azUserAcountService;

    /**
     * 查询用户账户列表
     */
    @PreAuthorize("@ss.hasPermi('system:acount:list')")
    @GetMapping("/list")
    public TableDataInfo list(AzUserAcount azUserAcount)
    {
        startPage();
        List<AzUserAcount> list = azUserAcountService.selectAzUserAcountList(azUserAcount);
        return getDataTable(list);
    }

    /**
     * 导出用户账户列表
     */
    @PreAuthorize("@ss.hasPermi('system:acount:export')")
    @Log(title = "用户账户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AzUserAcount azUserAcount)
    {
        List<AzUserAcount> list = azUserAcountService.selectAzUserAcountList(azUserAcount);
        ExcelUtil<AzUserAcount> util = new ExcelUtil<AzUserAcount>(AzUserAcount.class);
        return util.exportExcel(list, "acount");
    }

    /**
     * 获取用户账户详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:acount:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(azUserAcountService.selectAzUserAcountById(id));
    }

    /**
     * 新增用户账户
     */
    @PreAuthorize("@ss.hasPermi('system:acount:add')")
    @Log(title = "用户账户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AzUserAcount azUserAcount)
    {
        return toAjax(azUserAcountService.insertAzUserAcount(azUserAcount));
    }

    /**
     * 修改用户账户
     */
    @PreAuthorize("@ss.hasPermi('system:acount:edit')")
    @Log(title = "用户账户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AzUserAcount azUserAcount)
    {
        return toAjax(azUserAcountService.updateAzUserAcount(azUserAcount));
    }

    /**
     * 删除用户账户
     */
    @PreAuthorize("@ss.hasPermi('system:acount:remove')")
    @Log(title = "用户账户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(azUserAcountService.deleteAzUserAcountByIds(ids));
    }
}
