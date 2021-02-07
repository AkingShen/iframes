package com.iframe.interfaces.model.dto;

import javax.persistence.Column;

public class EquipmentInfoDto {

    private String batchNo;

    private String name;

    private String type;

    private Integer expTime;

    private String userId;

    private String pdCompany;

    private Integer status;

    private String solute;

    private String remark;

    private Integer id;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
