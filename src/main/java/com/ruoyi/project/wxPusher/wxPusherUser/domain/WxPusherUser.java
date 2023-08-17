package com.ruoyi.project.wxPusher.wxPusherUser.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 微信用户列表信息对象 wx_pusher_wx_user
 * 
 * @author besos
 * @date 2023-07-17
 */
public class WxPusherUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户微信uid */
    @Excel(name = "用户微信uid")
    private String uid;

    /** 用户关注的应用或者主题id */
    @Excel(name = "用户关注的应用或者主题id")
    private Long appOrTopicId;

    /** 是否拉黑Y拉黑 N正常 */
    @Excel(name = "是否拉黑Y拉黑 N正常")
    @JsonProperty("reject")
    private String reject;

    /** 关注类型，0：关注应用，1：关注topic */
    @Excel(name = "关注类型，0：关注应用，1：关注topic")
    private Long type;

    /** 关注的应用或者主题名字 */
    @Excel(name = "关注的应用或者主题名字")
    private String target;

    /** 0表示用户不是付费用户，大于0表示用户付费订阅到期时间，毫秒级时间戳 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "0表示用户不是付费用户，大于0表示用户付费订阅到期时间，毫秒级时间戳", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payEndTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getUid()
    {
        return uid;
    }
    public void setAppOrTopicId(Long appOrTopicId)
    {
        this.appOrTopicId = appOrTopicId;
    }

    public Long getAppOrTopicId()
    {
        return appOrTopicId;
    }
    @JsonSetter
    public void setReject(String reject)
    {
        this.reject = reject;
    }

    public String getReject()
    {
        return reject;
    }
    public void setType(Long type)
    {
        this.type = type;
    }

    public Long getType()
    {
        return type;
    }
    public void setTarget(String target)
    {
        this.target = target;
    }

    public String getTarget()
    {
        return target;
    }
    public void setPayEndTime(Date payEndTime)
    {
        this.payEndTime = payEndTime;
    }

    public Date getPayEndTime()
    {
        return payEndTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("appOrTopicId", getAppOrTopicId())
            .append("reject", getReject())
            .append("type", getType())
            .append("target", getTarget())
            .append("createTime", getCreateTime())
            .append("payEndTime", getPayEndTime())
            .toString();
    }
}
