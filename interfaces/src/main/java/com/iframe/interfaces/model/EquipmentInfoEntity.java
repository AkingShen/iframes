package com.iframe.interfaces.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iframe.interfaces.model.commonModel.CommonEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "equipment_info")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class EquipmentInfoEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "batch_no")
    private String batchNo;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "exp_time")
    private Integer expTime;

    @Column(name = "pd_company")
    private String pdCompany;

    @Column(name = "status")
    private Integer status;

    @Column(name = "solute")
    private String solute;

    @Column(name = "remark")
    private String remark;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_time")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getExpTime() {
        return expTime;
    }

    public void setExpTime(Integer expTime) {
        this.expTime = expTime;
    }

    public String getPdCompany() {
        return pdCompany;
    }

    public void setPdCompany(String pdCompany) {
        this.pdCompany = pdCompany;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSolute() {
        return solute;
    }

    public void setSolute(String solute) {
        this.solute = solute;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
