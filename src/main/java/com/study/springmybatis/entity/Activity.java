package com.study.springmybatis.entity;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Activity {

    private long id;
    private String title;
    private String imgPath;
    private String location;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date   date;
    private String intro;
    private String acitityType;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAcitityType() {
        return acitityType;
    }

    public void setAcitityType(String acitityType) {
        this.acitityType = acitityType;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", intro='" + intro + '\'' +
                ", acitityType='" + acitityType + '\'' +
                '}';
    }
}
