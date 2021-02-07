package com.iframe.interfaces.service.impl;

import com.iframe.common.utils.PageResult;
import com.iframe.interfaces.dao.MerchantInfoDao;
import com.iframe.interfaces.dao.UserAccountDao;
import com.iframe.interfaces.model.EquipmentInfoEntity;
import com.iframe.interfaces.model.MerchantInfoEntity;
import com.iframe.interfaces.model.UserAccountEntity;
import com.iframe.interfaces.model.dto.MerchantInfoDto;
import com.iframe.interfaces.service.IMerchantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class MerchantInfoService implements IMerchantInfoService {

    @Autowired
    MerchantInfoDao merchantInfoDao;

    @Autowired
    UserAccountDao userAccountDao;

    @Override
    public PageResult<MerchantInfoEntity> findByPage(String userId) {
        UserAccountEntity user = userAccountDao.getByUserId(userId);
        List<MerchantInfoEntity> list = merchantInfoDao.findByCreateUser(user.getUserName());
        PageResult<MerchantInfoEntity> page = new PageResult<>();
        page.setData(list);
        page.setDraw(1);
        page.setRecordsTotal(Integer.valueOf(list.size()));
        page.setRecordsFiltered(Integer.valueOf(list.size()));
        return page;
    }

    @Override
    public boolean addMerchantInfo(MerchantInfoDto merchantInfoDto) {
        UserAccountEntity user = userAccountDao.getByUserId(merchantInfoDto.getUserId());
        MerchantInfoEntity entity  = new MerchantInfoEntity();
        entity.setCreateUser(user.getUserName());
        entity.setMerchantNo(merchantInfoDto.getMerchantNo());
        entity.setMerchantName(merchantInfoDto.getMerchantName());
        entity.setTaxNo(merchantInfoDto.getTaxNo());
        entity.setLegelPerson(merchantInfoDto.getLegelPerson());
        entity.setLegelPhone(merchantInfoDto.getLegelPhone());
        entity.setSex(merchantInfoDto.getSex());
        entity.setAreaCode(merchantInfoDto.getAreaCode());
        entity.setAdress(merchantInfoDto.getAdress());
        entity.setCreateTime(new Date());
       if(null != merchantInfoDao.save(entity)){
            return true;
        }
        return false;
    }

    @Override
    public MerchantInfoEntity getMerchanrById(Integer id) {
        return merchantInfoDao.getOne(id);
    }
}
