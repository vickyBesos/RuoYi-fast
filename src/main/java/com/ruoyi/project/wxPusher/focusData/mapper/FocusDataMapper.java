package com.ruoyi.project.wxPusher.focusData.mapper;

import java.util.List;
import com.ruoyi.project.wxPusher.focusData.domain.FocusData;

/**
 * focusDataMapper接口
 * 
 * @author besos
 * @date 2023-07-17
 */
public interface FocusDataMapper 
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
     * 修改focusData
     * 
     * @param focusData focusData
     * @return 结果
     */
    public int updateFocusData(FocusData focusData);

    /**
     * 删除focusData
     * 
     * @param id focusData主键
     * @return 结果
     */
    public int deleteFocusDataById(Long id);

    /**
     * 批量删除focusData
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFocusDataByIds(String[] ids);

    /**
     * 查询focusData列表
     *
     * @param uid 微信uid
     * @return focusData
     */
    FocusData selectFocusDataByUId(String uid);
}
