package com.iframe.interfaces.service.impl;

import com.iframe.interfaces.dao.testDao.SysDictDao;
import com.iframe.interfaces.model.testModel.SysDictEntity;
import com.iframe.interfaces.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDictServiceImpl implements ISysDictService {

    @Autowired
    SysDictDao sysDictDao;

    @Override
    public SysDictEntity findByLable(String lable) {
        return sysDictDao.findByLable(lable);
    }
}
