package com.ruoyi.project.wxPusher.wxPusherUser.service;

import java.util.List;
import com.ruoyi.project.wxPusher.wxPusherUser.domain.WxPusherUser;

/**
 * 微信用户列表信息Service接口
 * 
 * @author besos
 * @date 2023-07-17
 */
public interface IWxPusherUserService 
{
    /**
     * 查询微信用户列表信息
     * 
     * @param id 微信用户列表信息主键
     * @return 微信用户列表信息
     */
    public WxPusherUser selectWxPusherUserById(Long id);

    /**
     * 查询微信用户列表信息列表
     * 
     * @param wxPusherUser 微信用户列表信息
     * @return 微信用户列表信息集合
     */
    public List<WxPusherUser> selectWxPusherUserList(WxPusherUser wxPusherUser);

    /**
     * 新增微信用户列表信息
     * 
     * @param wxPusherUser 微信用户列表信息
     * @return 结果
     */
    public int insertWxPusherUser(WxPusherUser wxPusherUser);

    /**
     * 修改微信用户列表信息
     * 
     * @param wxPusherUser 微信用户列表信息
     * @return 结果
     */
    public int updateWxPusherUser(WxPusherUser wxPusherUser);

    /**
     * 批量删除微信用户列表信息
     * 
     * @param ids 需要删除的微信用户列表信息主键集合
     * @return 结果
     */
    public int deleteWxPusherUserByIds(String ids);

    /**
     * 删除微信用户列表信息信息
     * 
     * @param id 微信用户列表信息主键
     * @return 结果
     */
    public int deleteWxPusherUserById(Long id);
}
