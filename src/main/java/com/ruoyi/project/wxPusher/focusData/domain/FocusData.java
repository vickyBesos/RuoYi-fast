package com.ruoyi.project.wxPusher.focusData.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * focusData对象 wx_pusher_focus_data
 * 
 * @author besos
 * @date 2023-07-17
 */
public class FocusData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 项目appId */
    @Excel(name = "项目appId")
    private Long appId;

    /** 项目appKey */
    @Excel(name = "项目appKey")
    private String appKey;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String appName;

    /** 关注方式 */
    @Excel(name = "关注方式")
    private String source;

    /** 关注时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "关注时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    /** 微信uid */
    @Excel(name = "微信uid")
    private String uid;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAppId(Long appId)
    {
        this.appId = appId;
    }

    public Long getAppId()
    {
        return appId;
    }
    public void setAppKey(String appKey)
    {
        this.appKey = appKey;
    }

    public String getAppKey()
    {
        return appKey;
    }
    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public String getAppName()
    {
        return appName;
    }
    public void setSource(String source)
    {
        this.source = source;
    }

    public String getSource()
    {
        return source;
    }
    public void setTime(Date time)
    {
        this.time = time;
    }

    public Date getTime()
    {
        return time;
    }
    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getUid()
    {
        return uid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("appId", getAppId())
            .append("appKey", getAppKey())
            .append("appName", getAppName())
            .append("source", getSource())
            .append("time", getTime())
            .append("uid", getUid())
            .toString();
    }
}
