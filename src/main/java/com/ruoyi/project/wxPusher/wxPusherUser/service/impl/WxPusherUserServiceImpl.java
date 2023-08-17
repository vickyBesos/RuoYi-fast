package com.ruoyi.project.wxPusher.wxPusherUser.service.impl;

import java.io.IOException;
import java.util.List;

import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import org.apache.commons.codec.CharEncoding;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import com.ruoyi.project.wxPusher.wxPusherUser.mapper.WxPusherUserMapper;
import com.ruoyi.project.wxPusher.wxPusherUser.domain.WxPusherUser;
import com.ruoyi.project.wxPusher.wxPusherUser.service.IWxPusherUserService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 微信用户列表信息Service业务层处理
 * 
 * @author besos
 * @date 2023-07-17
 */
@Service
public class WxPusherUserServiceImpl implements IWxPusherUserService 
{
    @Autowired
    private WxPusherUserMapper wxPusherUserMapper;

    /**
     * 查询微信用户列表信息
     * 
     * @param id 微信用户列表信息主键
     * @return 微信用户列表信息
     */
    @Override
    public WxPusherUser selectWxPusherUserById(Long id)
    {
        return wxPusherUserMapper.selectWxPusherUserById(id);
    }

    /**
     * 查询微信用户列表信息列表
     * 
     * @param wxPusherUser 微信用户列表信息
     * @return 微信用户列表信息
     */
    @Override
    public List<WxPusherUser> selectWxPusherUserList(WxPusherUser wxPusherUser)
    {
        // 查看所有用户信息
        String ret = HttpUtils.sendGet("https://wxpusher.zjiecode.com/api/fun/wxuser/v2", "appToken=AT_qqrIYcDK7AVyWwGw4NEiRBpeLSEhn9X4"+
                "&uid=" + wxPusherUser.getUid());
        JSONObject jsonObject = JSONObject.parseObject(ret);
        String data = jsonObject.getString("data");
        JSONObject dataObject = JSONObject.parseObject(data);
        String records = dataObject.getString("records");

        return JSONObject.parseArray(records, WxPusherUser.class);
    }
    /**
     * 发送消息
     */
    private void sendMsg(){
        JSONObject params = new JSONObject();
        params.put("appToken","AT_qqrIYcDK7AVyWwGw4NEiRBpeLSEhn9X4");
        params.put("content","测试发送内容");
        params.put("summary","测试消息摘要");
        params.put("contentType",1);
        String[] names = new String[1];
        names[0] = "UID_CrlqduItiLpCrxQS4odthBLR3W4H";
        params.put("uids",names);
        params.put("url","http://naturenexus.free.idcfengye.com");
        params.put("verifyPay",false);
        String s = HttpUtils.sendPost("https://wxpusher.zjiecode.com/api/send/message", params.toString());
        System.out.println(s);
    }

    /**
     * 删除消息
     */

    private void deleteMsgById(){
//        delete("https://wxpusher.zjiecode.com/api/send/message?messageContentId=605328165&appToken=AT_qqrIYcDK7AVyWwGw4NEiRBpeLSEhn9X4");
    }


    /**
     * 新增微信用户列表信息
     * 
     * @param wxPusherUser 微信用户列表信息
     * @return 结果
     */
    @Override
    public int insertWxPusherUser(WxPusherUser wxPusherUser)
    {
        wxPusherUser.setCreateTime(DateUtils.getNowDate());
        return wxPusherUserMapper.insertWxPusherUser(wxPusherUser);
    }

    /**
     * 修改微信用户列表信息
     * 
     * @param wxPusherUser 微信用户列表信息
     * @return 结果
     */
    @Override
    public int updateWxPusherUser(WxPusherUser wxPusherUser)
    {
        return wxPusherUserMapper.updateWxPusherUser(wxPusherUser);
    }

    /**
     * 批量删除微信用户列表信息
     * 
     * @param ids 需要删除的微信用户列表信息主键
     * @return 结果
     */
    @Override
    public int deleteWxPusherUserByIds(String ids)
    {
        return wxPusherUserMapper.deleteWxPusherUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除微信用户列表信息信息
     * 
     * @param id 微信用户列表信息主键
     * @return 结果
     */
    @Override
    public int deleteWxPusherUserById(Long id)
    {
        return wxPusherUserMapper.deleteWxPusherUserById(id);
    }
}
