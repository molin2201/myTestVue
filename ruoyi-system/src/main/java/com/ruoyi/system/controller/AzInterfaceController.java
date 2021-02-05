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
import com.ruoyi.system.domain.AzInterface;
import com.ruoyi.system.service.IAzInterfaceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 接口配置Controller
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@RestController
@RequestMapping("/system/interface")
public class AzInterfaceController extends BaseController
{
    @Autowired
    private IAzInterfaceService azInterfaceService;

    /**
     * 查询接口配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:interface:list')")
    @GetMapping("/list")
    public TableDataInfo list(AzInterface azInterface)
    {
        startPage();
        List<AzInterface> list = azInterfaceService.selectAzInterfaceList(azInterface);
        return getDataTable(list);
    }

    /**
     * 导出接口配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:interface:export')")
    @Log(title = "接口配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AzInterface azInterface)
    {
        List<AzInterface> list = azInterfaceService.selectAzInterfaceList(azInterface);
        ExcelUtil<AzInterface> util = new ExcelUtil<AzInterface>(AzInterface.class);
        return util.exportExcel(list, "interface");
    }

    /**
     * 获取接口配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:interface:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(azInterfaceService.selectAzInterfaceById(id));
    }

    /**
     * 新增接口配置
     */
    @PreAuthorize("@ss.hasPermi('system:interface:add')")
    @Log(title = "接口配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AzInterface azInterface)
    {
        return toAjax(azInterfaceService.insertAzInterface(azInterface));
    }

    /**
     * 修改接口配置
     */
    @PreAuthorize("@ss.hasPermi('system:interface:edit')")
    @Log(title = "接口配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AzInterface azInterface)
    {
        return toAjax(azInterfaceService.updateAzInterface(azInterface));
    }

    /**
     * 删除接口配置
     */
    @PreAuthorize("@ss.hasPermi('system:interface:remove')")
    @Log(title = "接口配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(azInterfaceService.deleteAzInterfaceByIds(ids));
    }
}
