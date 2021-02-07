package com.iframe.interfaces.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iframe.interfaces.model.systemModel.AreaInfoEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "merchant_info")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class MerchantInfoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "merchant_no")
    private String merchantNo;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "legel_person")
    private String legelPerson;

    @Column(name = "sex")
    private String sex;

    @Column(name = "legel_phone")
    private String legelPhone;

    @Column(name = "adress")
    private String adress;


    @Column(name = "areaCode")
    private String areaCode;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "areaCode", insertable = false, updatable = false)
    private AreaInfoEntity areaInfoEntity;

    @Column(name = "tax_no")
    private String taxNo;

    @Column(name = "merchant_create_time")
    private Date merchantCreateTime;

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

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getLegelPerson() {
        return legelPerson;
    }

    public void setLegelPerson(String legelPerson) {
        this.legelPerson = legelPerson;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLegelPhone() {
        return legelPhone;
    }

    public void setLegelPhone(String legelPhone) {
        this.legelPhone = legelPhone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public Date getMerchantCreateTime() {
        return merchantCreateTime;
    }

    public void setMerchantCreateTime(Date merchantCreateTime) {
        this.merchantCreateTime = merchantCreateTime;
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

    public AreaInfoEntity getAreaInfoEntity() {
        return areaInfoEntity;
    }

    public void setAreaInfoEntity(AreaInfoEntity areaInfoEntity) {
        this.areaInfoEntity = areaInfoEntity;
    }
}
