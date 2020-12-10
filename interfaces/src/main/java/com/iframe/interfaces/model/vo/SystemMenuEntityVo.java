package com.iframe.interfaces.model.vo;

import com.iframe.interfaces.model.systemModel.SystemMenuEntity;

import java.util.List;

public class SystemMenuEntityVo {

    private Integer id;

    private String pId;

    private String url;

    private String label;

    private String name;

    private String sort;

    private String icon;

    private List<SystemMenuEntity> childMenu;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<SystemMenuEntity> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<SystemMenuEntity> childMenu) {
        this.childMenu = childMenu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
