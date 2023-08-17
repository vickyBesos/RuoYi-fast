package com.ruoyi.project.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.api.dto.Callback;
import com.ruoyi.project.wxPusher.focusData.service.IFocusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/wxPusher")
public class WxPusherCallbackApi {


    @Autowired
    private IFocusDataService focusDataService;

    @PostMapping("/subscribe")
    public AjaxResult callback(@RequestBody Callback callback){
        System.err.println(callback.getAction());
        System.err.println(callback.getFocusData());
        int i = focusDataService.insertFocusData(callback.getFocusData());
        return AjaxResult.success(i);
    }

    @PostMapping("/data")
    public String processSubscribe(HttpServletRequest request, @RequestBody String jsonData) {
        // 使用HttpServletRequest对象获取参数值
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        String action = jsonObject.getString("action");
        JSONObject data = JSONObject.parseObject(jsonObject.getString("data"));

        int appId = data.getInteger("appId");
        String appKey = data.getString("appKey");
        String appName = data.getString("appName");
        String source = data.getString("source");
        String userName = data.getString("userName");
        String userHeadImg = data.getString("userHeadImg");
        long time = data.getLong("time");
        String uid = data.getString("uid");
        String extra = data.getString("extra");
        // 处理接收请求的逻辑
        // ...
        System.out.println(appId);
        System.out.println(appKey);
        System.out.println(appName);
        System.out.println(source);
        System.out.println(userName);
        System.out.println(userHeadImg);
        System.out.println(time);
        System.out.println(uid);
        System.out.println(extra);
        return "Success";
    }
}
