package com.iframe.interfaces.service.impl;

import com.iframe.common.utils.JwtUtils;
import com.iframe.common.utils.PasswordUtil;
import com.iframe.common.utils.RedisUtils;
import com.iframe.interfaces.dao.UserAccountDao;
import com.iframe.interfaces.model.UserAccountEntity;
import com.iframe.interfaces.model.dto.LoginDto;
import com.iframe.interfaces.model.vo.LoginVo;
import com.iframe.interfaces.service.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserAccountServiceImpl implements IUserAccountService {

    @Autowired
    RedisUtils redisUtils;


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
    public LoginVo checkUser(LoginDto entity, UserAccountEntity entityDb) throws Exception {
        LoginVo loginVo = new LoginVo();
        if(PasswordUtil.verify(entity.getPassword(),entityDb.getPassword())){
            String token =  "";
            //生成token
            try{
                token =  JwtUtils.createToken(entityDb.getUserId(),entityDb.getRealName(),entity.getUserName());
            }catch (Exception e){
                throw new Exception("生成令牌错误");
            }
            //生成缓存
            redisUtils.set(entityDb.getUserId(),token,0);

            loginVo.setUserId(entityDb.getUserId());
            loginVo.setToken(token);
            return  loginVo;
        }
        return null;
    }

    @Override
    public UserAccountEntity getByUserId(String userId) {
        return userAccountDao.getByUserId(userId);
    }


    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }
}
