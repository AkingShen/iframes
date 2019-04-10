package com.iframe.interfaces.model.testModel;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sys_dict")
public class SysDictEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "lable")
    private String lable;

    @Column(name = "value")
    private String value;

    @Column(name = "text")
    private String text;

    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String label) {
        this.lable = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
