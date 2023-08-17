package com.ruoyi.project.wxPusher.wxPusherUser.controller;

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
import com.ruoyi.project.wxPusher.wxPusherUser.domain.WxPusherUser;
import com.ruoyi.project.wxPusher.wxPusherUser.service.IWxPusherUserService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 微信用户列表信息Controller
 * 
 * @author besos
 * @date 2023-07-17
 */
@Controller
@RequestMapping("/wxPusher/wxPusherUser")
public class WxPusherUserController extends BaseController
{
    private String prefix = "wxPusher/wxPusherUser";

    @Autowired
    private IWxPusherUserService wxPusherUserService;

    @RequiresPermissions("wxPusher:wxPusherUser:view")
    @GetMapping()
    public String wxPusherUser()
    {
        return prefix + "/wxPusherUser";
    }

    /**
     * 查询微信用户列表信息列表
     */
    @RequiresPermissions("wxPusher:wxPusherUser:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WxPusherUser wxPusherUser)
    {
        startPage();
        List<WxPusherUser> list = wxPusherUserService.selectWxPusherUserList(wxPusherUser);
        return getDataTable(list);
    }

    /**
     * 导出微信用户列表信息列表
     */
    @RequiresPermissions("wxPusher:wxPusherUser:export")
    @Log(title = "微信用户列表信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WxPusherUser wxPusherUser)
    {
        List<WxPusherUser> list = wxPusherUserService.selectWxPusherUserList(wxPusherUser);
        ExcelUtil<WxPusherUser> util = new ExcelUtil<WxPusherUser>(WxPusherUser.class);
        return util.exportExcel(list, "微信用户列表信息数据");
    }

    /**
     * 新增微信用户列表信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存微信用户列表信息
     */
    @RequiresPermissions("wxPusher:wxPusherUser:add")
    @Log(title = "微信用户列表信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WxPusherUser wxPusherUser)
    {
        return toAjax(wxPusherUserService.insertWxPusherUser(wxPusherUser));
    }

    /**
     * 修改微信用户列表信息
     */
    @RequiresPermissions("wxPusher:wxPusherUser:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        WxPusherUser wxPusherUser = wxPusherUserService.selectWxPusherUserById(id);
        mmap.put("wxPusherUser", wxPusherUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存微信用户列表信息
     */
    @RequiresPermissions("wxPusher:wxPusherUser:edit")
    @Log(title = "微信用户列表信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WxPusherUser wxPusherUser)
    {
        return toAjax(wxPusherUserService.updateWxPusherUser(wxPusherUser));
    }

    /**
     * 删除微信用户列表信息
     */
    @RequiresPermissions("wxPusher:wxPusherUser:remove")
    @Log(title = "微信用户列表信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wxPusherUserService.deleteWxPusherUserByIds(ids));
    }
}
