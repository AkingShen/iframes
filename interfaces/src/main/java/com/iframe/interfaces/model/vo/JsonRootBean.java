package com.iframe.interfaces.model.vo;

import java.util.List;

/**
 * Auto-generated: 2021-02-04 15:26:36
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private Suggestion suggestion;
    private String count;
    private String infocode;
    private List<Pois> pois;
    private String status;
    private String info;
    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }
    public Suggestion getSuggestion() {
        return suggestion;
    }

    public void setCount(String count) {
        this.count = count;
    }
    public String getCount() {
        return count;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }
    public String getInfocode() {
        return infocode;
    }

    public void setPois(List<Pois> pois) {
        this.pois = pois;
    }
    public List<Pois> getPois() {
        return pois;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }

}
