package com.ruoyi.project.wxPusher.focusData.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.wxPusher.focusData.mapper.FocusDataMapper;
import com.ruoyi.project.wxPusher.focusData.domain.FocusData;
import com.ruoyi.project.wxPusher.focusData.service.IFocusDataService;
import com.ruoyi.common.utils.text.Convert;

/**
 * focusDataService业务层处理
 * 
 * @author besos
 * @date 2023-07-17
 */
@Service
public class FocusDataServiceImpl implements IFocusDataService 
{
    @Autowired
    private FocusDataMapper focusDataMapper;

    /**
     * 查询focusData
     * 
     * @param id focusData主键
     * @return focusData
     */
    @Override
    public FocusData selectFocusDataById(Long id)
    {
        return focusDataMapper.selectFocusDataById(id);
    }

    /**
     * 查询focusData列表
     * 
     * @param focusData focusData
     * @return focusData
     */
    @Override
    public List<FocusData> selectFocusDataList(FocusData focusData)
    {
        return focusDataMapper.selectFocusDataList(focusData);
    }

    /**
     * 新增focusData
     * 
     * @param focusData focusData
     * @return 结果
     */
    @Override
    public int insertFocusData(FocusData focusData)
    {
        FocusData retData = selectFocusDataByUId(focusData.getUid());
        if (retData == null){
            return focusDataMapper.insertFocusData(focusData);
        }
        focusData.setId(retData.getId());
        return updateFocusData(focusData);
    }

    /**
     * 查询focusData列表
     *
     * @param uid 微信uid
     * @return focusData
     */
    @Override
    public FocusData selectFocusDataByUId(String uid)
    {
        return focusDataMapper.selectFocusDataByUId(uid);
    }


    /**
     * 修改focusData
     * 
     * @param focusData focusData
     * @return 结果
     */
    @Override
    public int updateFocusData(FocusData focusData)
    {
        return focusDataMapper.updateFocusData(focusData);
    }

    /**
     * 批量删除focusData
     * 
     * @param ids 需要删除的focusData主键
     * @return 结果
     */
    @Override
    public int deleteFocusDataByIds(String ids)
    {
        return focusDataMapper.deleteFocusDataByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除focusData信息
     * 
     * @param id focusData主键
     * @return 结果
     */
    @Override
    public int deleteFocusDataById(Long id)
    {
        return focusDataMapper.deleteFocusDataById(id);
    }
}
