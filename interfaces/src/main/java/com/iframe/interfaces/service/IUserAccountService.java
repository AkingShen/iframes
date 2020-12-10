package com.iframe.interfaces.service;

import com.iframe.interfaces.model.UserAccountEntity;
import com.iframe.interfaces.model.dto.LoginDto;
import com.iframe.interfaces.model.vo.LoginVo;
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
    LoginVo checkUser(LoginDto entity, UserAccountEntity entitDb) throws Exception;

    /**
     *
     * 根据用户Id获取用户实体
     * @param userId
     * @return
     */
    UserAccountEntity getByUserId(String userId);

}
