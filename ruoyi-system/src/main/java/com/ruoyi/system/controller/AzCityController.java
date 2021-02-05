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
import com.ruoyi.system.domain.AzCity;
import com.ruoyi.system.service.IAzCityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 城市号码Controller
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@RestController
@RequestMapping("/system/city")
public class AzCityController extends BaseController
{
    @Autowired
    private IAzCityService azCityService;

    /**
     * 查询城市号码列表
     */
    @PreAuthorize("@ss.hasPermi('system:city:list')")
    @GetMapping("/list")
    public TableDataInfo list(AzCity azCity)
    {
        startPage();
        List<AzCity> list = azCityService.selectAzCityList(azCity);
        return getDataTable(list);
    }

    /**
     * 导出城市号码列表
     */
    @PreAuthorize("@ss.hasPermi('system:city:export')")
    @Log(title = "城市号码", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AzCity azCity)
    {
        List<AzCity> list = azCityService.selectAzCityList(azCity);
        ExcelUtil<AzCity> util = new ExcelUtil<AzCity>(AzCity.class);
        return util.exportExcel(list, "city");
    }

    /**
     * 获取城市号码详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:city:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(azCityService.selectAzCityById(id));
    }

    /**
     * 新增城市号码
     */
    @PreAuthorize("@ss.hasPermi('system:city:add')")
    @Log(title = "城市号码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AzCity azCity)
    {
        return toAjax(azCityService.insertAzCity(azCity));
    }

    /**
     * 修改城市号码
     */
    @PreAuthorize("@ss.hasPermi('system:city:edit')")
    @Log(title = "城市号码", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AzCity azCity)
    {
        return toAjax(azCityService.updateAzCity(azCity));
    }

    /**
     * 删除城市号码
     */
    @PreAuthorize("@ss.hasPermi('system:city:remove')")
    @Log(title = "城市号码", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(azCityService.deleteAzCityByIds(ids));
    }
}
