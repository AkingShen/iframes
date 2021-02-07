package com.iframe.interfaces.model.dto;

import javax.persistence.Column;
import java.util.Date;

public class MerchantInfoDto {

    private String userId;

    private String merchantNo;


    private String merchantName;


    private String legelPerson;


    private String sex;


    private String legelPhone;


    private String adress;


    private String areaCode;

    private String taxNo;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
