package com.iframe.interfaces.service.impl;

import com.iframe.common.utils.JwtUtils;
import com.iframe.common.utils.PasswordUtil;
import com.iframe.interfaces.dao.UserAccountDao;
import com.iframe.interfaces.model.UserAccountEntity;
import com.iframe.interfaces.service.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserAccountServiceImpl implements IUserAccountService {

    @Autowired
    UserAccountDao userAccountDao;

    @Override
    public boolean  addUserAccount(UserAccountEntity entity) {
        //检查是否存在
        //MD5加盐存储
        String password = PasswordUtil.generate(entity.getPassword());
        entity.setPassword(password);
        entity.setUserId(getUUID());
        entity.setCreateTime(new Date());
        if(null != userAccountDao.saveAndFlush(entity)){
            return true;
        }
        return false;
    }

    @Override
    public UserAccountEntity getByUserName(String userName) {
        return userAccountDao.getByUserName(userName);
    }

    @Override
    public String checkUser(UserAccountEntity entity,UserAccountEntity entitDb) {
        if(PasswordUtil.verify(entity.getPassword(),entitDb.getPassword())){
            //生成token
            return  JwtUtils.createToken(entitDb.getUserId(),entitDb.getRealName(),entity.getUserName());
        }
        return null;
    }


    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }
}
