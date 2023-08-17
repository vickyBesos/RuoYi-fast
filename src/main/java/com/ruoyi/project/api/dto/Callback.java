package com.ruoyi.project.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.project.wxPusher.focusData.domain.FocusData;
import lombok.Data;

@Data
public class Callback {
    private String action;
    @JsonProperty("data")
    private FocusData focusData;

    @Override
    public String toString() {
        return "Callback{" +
                "action='" + action + '\'' +
                ", focusData=" + focusData +
                '}';
    }
}