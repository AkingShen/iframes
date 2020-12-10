package com.iframe.interfaces.model.vo;

import com.iframe.interfaces.model.UserAccountEntity;
import com.iframe.interfaces.model.systemModel.SystemMenuEntity;

import java.util.List;

public class RoleInfoVo {

    private List<SystemMenuEntityVo> pMenu;

    public List<SystemMenuEntityVo> getpMenu() {
        return pMenu;
    }

    public void setpMenu(List<SystemMenuEntityVo> pMenu) {
        this.pMenu = pMenu;
    }
}
