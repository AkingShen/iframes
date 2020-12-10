package com.iframe.interfaces.service.impl;

import com.iframe.interfaces.dao.systemDao.SystemRoleInfoDao;
import com.iframe.interfaces.model.systemModel.SystemMenuEntity;
import com.iframe.interfaces.model.systemModel.SystemRoleInfoEntity;
import com.iframe.interfaces.model.vo.RoleInfoVo;
import com.iframe.interfaces.model.vo.SystemMenuEntityVo;
import com.iframe.interfaces.service.ISystemRoleInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SystemRoleInfoServiceImpl implements ISystemRoleInfoService {

    @Autowired
    SystemRoleInfoDao systemRoleInfoDao;


    @Override
    public RoleInfoVo findByRoleName(String roleName) {
        List<SystemRoleInfoEntity> list = systemRoleInfoDao.findByRoleName(roleName);
        RoleInfoVo roleInfoVo = new RoleInfoVo();
        List<SystemMenuEntityVo> pMenu =  new ArrayList<>();
        List<SystemMenuEntity> listMenu = new ArrayList<>();
        if(list.size() > 0) {
            for (SystemRoleInfoEntity sr : list) {
                listMenu.add(sr.getSystemMenuEntity());
            }
            //选出父节点
            List<SystemMenuEntity> pMenuTemp = listMenu.stream().filter(s -> s.getpId().equals("0")).collect(Collectors.toList());
            //组装父节点
            for(SystemMenuEntity sr : pMenuTemp){
                SystemMenuEntityVo sv = new SystemMenuEntityVo();
                BeanUtils.copyProperties(sr,sv);
                pMenu.add(sv);
                roleInfoVo.setpMenu(pMenu);
                //组装字节点
                List<SystemMenuEntity> cMenuTemp = listMenu.stream().filter(s -> s.getpId().equals(String.valueOf(sv.getId()))).collect(Collectors.toList());
                if(cMenuTemp.size() > 0 ){
                    sv.setChildMenu(cMenuTemp);
                }
            }

        }else{
            roleInfoVo.setpMenu(null);
        }
        return roleInfoVo;
    }
}
