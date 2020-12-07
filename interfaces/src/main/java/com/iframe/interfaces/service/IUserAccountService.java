package com.iframe.interfaces.service;

import com.iframe.interfaces.model.UserAccountEntity;
import org.springframework.stereotype.Component;


@Component
public interface IUserAccountService {

    /**
     * 注册用户
     * @param entity
     * @return
     */
    boolean addUserAccount(UserAccountEntity entity);


    /**
     *
     * 根据用户名寻找用户实例
     */
    UserAccountEntity getByUserName(String userName);


    /**
     * 用户权限验证
     */
    String checkUser(UserAccountEntity entity,UserAccountEntity entitDb);

}
