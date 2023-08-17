package com.ruoyi.project.wxPusher.focusData.service;

import java.util.List;
import com.ruoyi.project.wxPusher.focusData.domain.FocusData;

/**
 * focusDataService接口
 * 
 * @author besos
 * @date 2023-07-17
 */
public interface IFocusDataService 
{
    /**
     * 查询focusData
     * 
     * @param id focusData主键
     * @return focusData
     */
    public FocusData selectFocusDataById(Long id);

    /**
     * 查询focusData列表
     * 
     * @param focusData focusData
     * @return focusData集合
     */
    public List<FocusData> selectFocusDataList(FocusData focusData);

    /**
     * 新增focusData
     * 
     * @param focusData focusData
     * @return 结果
     */
    public int insertFocusData(FocusData focusData);


    /**
     * 查询focusData列表
     *
     * @param uid 微信uid
     * @return focusData
     */
    public FocusData selectFocusDataByUId(String uid);

    /**
     * 修改focusData
     * 
     * @param focusData focusData
     * @return 结果
     */
    public int updateFocusData(FocusData focusData);

    /**
     * 批量删除focusData
     * 
     * @param ids 需要删除的focusData主键集合
     * @return 结果
     */
    public int deleteFocusDataByIds(String ids);

    /**
     * 删除focusData信息
     * 
     * @param id focusData主键
     * @return 结果
     */
    public int deleteFocusDataById(Long id);
}
