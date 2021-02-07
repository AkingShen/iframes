package com.iframe.interfaces.service.impl;


import com.iframe.common.utils.PageResult;
import com.iframe.interfaces.dao.EquipmentInfoDao;
import com.iframe.interfaces.dao.UserAccountDao;
import com.iframe.interfaces.model.EquipmentInfoEntity;
import com.iframe.interfaces.model.UserAccountEntity;
import com.iframe.interfaces.model.dto.EquipmentInfoDto;
import com.iframe.interfaces.service.IEquipmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EquipmentInfoService implements IEquipmentInfoService {

    @Autowired
    EquipmentInfoDao equipmentInfoDao;

    @Autowired
    UserAccountDao userAccountDao;

    @Override
    public boolean addEquipmentInfo(EquipmentInfoDto equipmentInfoDto) {
        EquipmentInfoEntity equ = new EquipmentInfoEntity();
        equ.setName(equipmentInfoDto.getName());
        equ.setBatchNo(equipmentInfoDto.getBatchNo());
        equ.setExpTime(equipmentInfoDto.getExpTime());
        equ.setSolute(equipmentInfoDto.getSolute());
        equ.setPdCompany(equipmentInfoDto.getPdCompany());
        equ.setStatus(1);
        equ.setType(equipmentInfoDto.getType());
        equ.setRemark(equipmentInfoDto.getRemark());
        if(null != equipmentInfoDto.getId()){
            equ.setId(equipmentInfoDto.getId());
        }
        UserAccountEntity user = userAccountDao.getByUserId(equipmentInfoDto.getUserId());
        if(user != null){
            equ.setCreateUser(user.getUserName());
        }
        equ.setCreateTime(new Date());
        if(equipmentInfoDao.save(equ) != null){
            return true;
        }
        return false;
    }

    @Override
    public PageResult<EquipmentInfoEntity> findByPage(String userId) {
        UserAccountEntity user = userAccountDao.getByUserId(userId);
        List<EquipmentInfoEntity> list = equipmentInfoDao.findByCreateUser(user.getUserName());
        PageResult<EquipmentInfoEntity> page = new PageResult<>();
        page.setData(list);
        page.setDraw(1);
        page.setRecordsTotal(Integer.valueOf(list.size()));
        page.setRecordsFiltered(Integer.valueOf(list.size()));
        return  page;
    }

    @Override
    public EquipmentInfoEntity getEquById(Integer id) {
        return equipmentInfoDao.getOne(id);
    }

    @Override
    public boolean deleteById(Integer id) {
        try{
            equipmentInfoDao.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
