package com.iframe.interfaces.service.impl;


import com.iframe.interfaces.dao.systemDao.AreaInfoDao;
import com.iframe.interfaces.model.systemModel.AreaInfoEntity;
import com.iframe.interfaces.service.IAreaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaInfoServiceImpl implements IAreaInfoService {

    @Autowired
    AreaInfoDao areaInfoDao;

    @Override
    public List<AreaInfoEntity> findByLevelType(Integer type) {
        return areaInfoDao.findByLevelType(type);
    }

    @Override
    public List<AreaInfoEntity> findByParentId(Integer parenId) {
        return  areaInfoDao.findByParentId(parenId);
    }

    @Override
    public Integer getProvinceId(Integer cityId) {
        return areaInfoDao.getOne(cityId).getParentId();
    }

    @Override
    public AreaInfoEntity getById(Integer id) {
        return areaInfoDao.getOne(id);
    }
}
