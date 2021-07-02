package com.iframe.interfaces.model.systemModel;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "hospitals_wg_copy")
public class HospitalWgEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "hospital_alias")
    private String hospitalAlias;

    @Column(name = "amp_code")
    private String ampCode;

    @Column(name = "level")
    private String level;

    @Column(name = "type")
    private String type;

    @Column(name = "ad_code")
    private String adCode;

    @Column(name = "province")
    private String province;

    @Column(name = "province_code")
    private String provinceCode;


    @Column(name = "city")
    private String city;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "district")
    private String district;


    @Column(name = "district_code")
    private String districtCode;


    @Column(name = "adress")
    private String adress;


    @Column(name = "location_x")
    private String locationX;


    @Column(name = "location_y")
    private String locationY;

    @Column(name = "createTime")
    private Date createTime;

    @Column(name = "short_name")
    private String shortName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAlias() {
        return hospitalAlias;
    }

    public void setHospitalAlias(String hospitalAlias) {
        this.hospitalAlias = hospitalAlias;
    }

    public String getAmpCode() {
        return ampCode;
    }

    public void setAmpCode(String ampCode) {
        this.ampCode = ampCode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
