package com.ruoyi.project.wxPusher.focusData.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.wxPusher.focusData.domain.FocusData;
import com.ruoyi.project.wxPusher.focusData.service.IFocusDataService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * focusDataController
 * 
 * @author besos
 * @date 2023-07-17
 */
@Controller
@RequestMapping("/wxPusher/focusData")
public class FocusDataController extends BaseController
{
    private String prefix = "wxPusher/focusData";

    @Autowired
    private IFocusDataService focusDataService;

    @RequiresPermissions("wxPusher:focusData:view")
    @GetMapping()
    public String focusData()
    {
        return prefix + "/focusData";
    }

    /**
     * 查询focusData列表
     */
    @RequiresPermissions("wxPusher:focusData:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FocusData focusData)
    {
        startPage();
        List<FocusData> list = focusDataService.selectFocusDataList(focusData);
        return getDataTable(list);
    }

    /**
     * 导出focusData列表
     */
    @RequiresPermissions("wxPusher:focusData:export")
    @Log(title = "focusData", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FocusData focusData)
    {
        List<FocusData> list = focusDataService.selectFocusDataList(focusData);
        ExcelUtil<FocusData> util = new ExcelUtil<FocusData>(FocusData.class);
        return util.exportExcel(list, "focusData数据");
    }

    /**
     * 新增focusData
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存focusData
     */
    @RequiresPermissions("wxPusher:focusData:add")
    @Log(title = "focusData", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FocusData focusData)
    {
        return toAjax(focusDataService.insertFocusData(focusData));
    }

    /**
     * 修改focusData
     */
    @RequiresPermissions("wxPusher:focusData:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        FocusData focusData = focusDataService.selectFocusDataById(id);
        mmap.put("focusData", focusData);
        return prefix + "/edit";
    }

    /**
     * 修改保存focusData
     */
    @RequiresPermissions("wxPusher:focusData:edit")
    @Log(title = "focusData", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FocusData focusData)
    {
        return toAjax(focusDataService.updateFocusData(focusData));
    }

    /**
     * 删除focusData
     */
    @RequiresPermissions("wxPusher:focusData:remove")
    @Log(title = "focusData", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(focusDataService.deleteFocusDataByIds(ids));
    }
}
