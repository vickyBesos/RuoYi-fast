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
public class OutMessage {
    /**
     * 开发者微信号
     */
    @XmlElement(name="FromUserName")
    protected String fromUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    @XmlElement(name="ToUserName")
    protected String toUserName;

    /**
     * 消息创建时间
     */
    @XmlElement(name="CreateTime")
    protected Long createTime;

    /**
     * 消息类型
     * text 文本消息
     * image 图片消息
     * voice 语音消息
     * video 视频消息
     * music 音乐消息
     */
    @XmlElement(name="MsgType")
    protected String msgType;

    /**
     * 文本内容
     */
    @XmlElement(name="Content")
    private String content;

    /**
     * 图片的媒体id列表
     */
    @XmlElementWrapper(name="Image") // 表示MediaId属性内嵌于<Image>标签
    @XmlElement(name = "MediaId")
    private String[] mediaId;
}
