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
}
