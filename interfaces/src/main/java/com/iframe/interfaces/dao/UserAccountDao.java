package com.iframe.interfaces.dao;

import com.iframe.interfaces.model.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserAccountDao extends JpaRepository<UserAccountEntity, Integer> {

    UserAccountEntity getByUserName(String userName);

}
