package com.iframe.interfaces.service;


import com.iframe.common.utils.PageResult;
import com.iframe.interfaces.model.EquipmentInfoEntity;
import com.iframe.interfaces.model.MerchantInfoEntity;
import com.iframe.interfaces.model.dto.MerchantInfoDto;
import org.springframework.stereotype.Component;

@Component
public interface IMerchantInfoService {

    PageResult<MerchantInfoEntity> findByPage(String userId);

    boolean addMerchantInfo(MerchantInfoDto merchantInfoDto);

    MerchantInfoEntity getMerchanrById(Integer id);
}
