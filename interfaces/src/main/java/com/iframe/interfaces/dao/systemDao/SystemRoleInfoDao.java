package com.iframe.interfaces.dao.systemDao;

import com.iframe.interfaces.model.systemModel.SystemRoleInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemRoleInfoDao extends JpaRepository<SystemRoleInfoEntity,Integer> {

    /**
     * 根据RoleName获取权限列表
     */
    List<SystemRoleInfoEntity> findByRoleName(String role);

}
