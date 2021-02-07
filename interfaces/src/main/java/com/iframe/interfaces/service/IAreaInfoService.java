package com.iframe.interfaces.service;


import com.iframe.interfaces.model.systemModel.AreaInfoEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IAreaInfoService {

    List<AreaInfoEntity> findByLevelType(Integer type);

    List<AreaInfoEntity> findByParentId(Integer parenId);

    Integer getProvinceId(Integer cityId);

    AreaInfoEntity getById(Integer id);
}
