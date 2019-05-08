package com.iframe.interfaces.model.testModel;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cron_config")
public class CronConfigEntity {
    @Id
    @GeneratedValue
    @Column(name = "cron_id")
    private  Integer cronId;

    @Column(name = "cron_express")
    private  String  cronExpress;

    @Column(name = "status")
    private  String  status;

    @Column(name = "cron_type")
    private  String  cronType;


    public Integer getCronId() {
        return cronId;
    }

    public void setCronId(Integer cronId) {
        this.cronId = cronId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCronType() {
        return cronType;
    }

    public void setCronType(String cronType) {
        this.cronType = cronType;
    }

    public String getCronExpress() {
        return cronExpress;
    }

    public void setCronExpress(String cronExpress) {
        this.cronExpress = cronExpress;
    }
}
