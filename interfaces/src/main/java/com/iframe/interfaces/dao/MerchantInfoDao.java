package com.iframe.interfaces.dao;

import com.iframe.interfaces.model.EquipmentInfoEntity;
import com.iframe.interfaces.model.MerchantInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MerchantInfoDao extends JpaRepository<MerchantInfoEntity, Integer> {

    List<MerchantInfoEntity> findByCreateUser(String createUser);
}
