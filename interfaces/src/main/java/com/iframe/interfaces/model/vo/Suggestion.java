package com.iframe.interfaces.model.vo;

/**
 * Copyright 2021 bejson.com
 */
import java.util.List;

/**
 * Auto-generated: 2021-02-04 15:26:36
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Suggestion {

    private List<String> keywords;
    private List<String> cities;
    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
    public List<String> getKeywords() {
        return keywords;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
    public List<String> getCities() {
        return cities;
    }

}
