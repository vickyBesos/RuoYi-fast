package com.ruoyi.project.system.wx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class InMessage {
    // 开发者微信号
    @XmlElement(name = "FromUserName")
    protected String fromUserName;
    // 发送方帐号（一个OpenID）
    @XmlElement(name = "ToUserName")
    protected String toUserName;
    // 消息创建时间
    @XmlElement(name = "CreateTime")
    protected Long createTime;
    /**
     * 消息类型
     * text 文本消息
     * image 图片消息
     * voice 语音消息
     * video 视频消息
     * music 音乐消息
     */
    @XmlElement(name = "MsgType")
    protected String msgType;
    // 消息id
    @XmlElement(name = "MsgId")
    protected Long msgId;
    // 文本内容
    @XmlElement(name = "Content")
    private String content;
    // 图片链接（由系统生成）
    @XmlElement(name = "PicUrl")
    private String picUrl;
    // 图片消息媒体id，可以调用多媒体文件下载接口拉取数据
    @XmlElement(name = "MediaId")
    private String mediaId;

    /**
     * 事件类型
     *  subscribe(订阅)
     *  unsubscribe(取消订阅)
     *  LOCATION(上报地理位置)
     *  CLICK(点击普通的菜单)
     *  VIEW(点击跳转链接的菜单)
     */
    @XmlElement(name = "Event")
    private String event;

}

