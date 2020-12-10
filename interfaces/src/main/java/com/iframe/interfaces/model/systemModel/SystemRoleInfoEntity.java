package com.iframe.interfaces.model.systemModel;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "system_role_info")
public class SystemRoleInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "system_menu_id")
    private String systemMenuId;

    @Column(name = "user_name")
    private String userName;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "system_menu_id", insertable = false, updatable = false)
    private SystemMenuEntity SystemMenuEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getSystemMenuId() {
        return systemMenuId;
    }

    public void setSystemMenuId(String systemMenuId) {
        this.systemMenuId = systemMenuId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public com.iframe.interfaces.model.systemModel.SystemMenuEntity getSystemMenuEntity() {
        return SystemMenuEntity;
    }

    public void setSystemMenuEntity(com.iframe.interfaces.model.systemModel.SystemMenuEntity systemMenuEntity) {
        SystemMenuEntity = systemMenuEntity;
    }
}
