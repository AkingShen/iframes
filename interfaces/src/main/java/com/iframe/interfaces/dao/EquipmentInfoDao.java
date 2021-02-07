package com.iframe.interfaces.dao;

import com.iframe.interfaces.model.EquipmentInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentInfoDao extends JpaRepository<EquipmentInfoEntity, Integer> {

    List<EquipmentInfoEntity> findByCreateUser(String createUser);
}
