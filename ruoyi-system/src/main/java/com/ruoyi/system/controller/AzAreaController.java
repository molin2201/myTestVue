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
import com.ruoyi.system.domain.AzArea;
import com.ruoyi.system.service.IAzAreaService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地区信息Controller
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@RestController
@RequestMapping("/system/area")
public class AzAreaController extends BaseController
{
    @Autowired
    private IAzAreaService azAreaService;

    /**
     * 查询地区信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:area:list')")
    @GetMapping("/list")
    public TableDataInfo list(AzArea azArea)
    {
        startPage();
        List<AzArea> list = azAreaService.selectAzAreaList(azArea);
        return getDataTable(list);
    }

    /**
     * 导出地区信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:area:export')")
    @Log(title = "地区信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AzArea azArea)
    {
        List<AzArea> list = azAreaService.selectAzAreaList(azArea);
        ExcelUtil<AzArea> util = new ExcelUtil<AzArea>(AzArea.class);
        return util.exportExcel(list, "area");
    }

    /**
     * 获取地区信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:area:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(azAreaService.selectAzAreaById(id));
    }

    /**
     * 新增地区信息
     */
    @PreAuthorize("@ss.hasPermi('system:area:add')")
    @Log(title = "地区信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AzArea azArea)
    {
        return toAjax(azAreaService.insertAzArea(azArea));
    }

    /**
     * 修改地区信息
     */
    @PreAuthorize("@ss.hasPermi('system:area:edit')")
    @Log(title = "地区信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AzArea azArea)
    {
        return toAjax(azAreaService.updateAzArea(azArea));
    }

    /**
     * 删除地区信息
     */
    @PreAuthorize("@ss.hasPermi('system:area:remove')")
    @Log(title = "地区信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(azAreaService.deleteAzAreaByIds(ids));
    }
}
