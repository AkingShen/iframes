package com.iframe.interfaces.service;


import com.iframe.common.utils.PageResult;
import com.iframe.interfaces.model.EquipmentInfoEntity;
import com.iframe.interfaces.model.dto.EquipmentInfoDto;
import org.springframework.stereotype.Component;

@Component
public interface IEquipmentInfoService{

    boolean addEquipmentInfo(EquipmentInfoDto equipmentInfoDto);


    PageResult<EquipmentInfoEntity> findByPage(String userId);


    EquipmentInfoEntity getEquById(Integer id);

    boolean deleteById(Integer id);
}
