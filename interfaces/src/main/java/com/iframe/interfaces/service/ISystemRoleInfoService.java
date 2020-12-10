package com.iframe.interfaces.service;


import com.iframe.interfaces.model.vo.RoleInfoVo;
import org.springframework.stereotype.Component;


@Component
public interface ISystemRoleInfoService {

    RoleInfoVo findByRoleName(String roleName);

}
