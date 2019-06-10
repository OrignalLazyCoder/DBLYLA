package com.downbeat.downbeat.Models;

import java.util.Date;

public class MeditateInformation {


    private String userId, copyright, name, fullPath, type;
    private Date uploadDate;
    private int likeCount, playCount;

    public MeditateInformation() {
    }

    public MeditateInformation(String userId, String copyright, String name, String fullPath, String type, Date uploadDate, int likeCount, int playCount) {
        this.userId = userId;
        this.copyright = copyright;
        this.name = name;
        this.fullPath = fullPath;
        this.type = type;
        this.uploadDate = uploadDate;
        this.likeCount = likeCount;
        this.playCount = playCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }
}
