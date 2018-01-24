package com.gf.musics.web.model;

import java.util.Date;

public class MusicLibrary {
    private Integer pkId;

    private String musicName;

    private String singerId;

    private String singerName;

    private String musicImgUrl;

    private String musicUrl;

    private String musicMvUrl;

    private String musicGeci;

    private String musicDuration;

    private Float musicSize;

    private Integer clicks;

    private String album;

    private Integer styleId;

    private Integer quality;

    private Integer status;

    private Date publishDate;

    private Integer deleteFlag;

    private Date createTime;

    private Date updateTime;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName == null ? null : musicName.trim();
    }

    public String getSingerId() {
        return singerId;
    }

    public void setSingerId(String singerId) {
        this.singerId = singerId == null ? null : singerId.trim();
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName == null ? null : singerName.trim();
    }

    public String getMusicImgUrl() {
        return musicImgUrl;
    }

    public void setMusicImgUrl(String musicImgUrl) {
        this.musicImgUrl = musicImgUrl == null ? null : musicImgUrl.trim();
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl == null ? null : musicUrl.trim();
    }

    public String getMusicMvUrl() {
        return musicMvUrl;
    }

    public void setMusicMvUrl(String musicMvUrl) {
        this.musicMvUrl = musicMvUrl == null ? null : musicMvUrl.trim();
    }

    public String getMusicGeci() {
        return musicGeci;
    }

    public void setMusicGeci(String musicGeci) {
        this.musicGeci = musicGeci == null ? null : musicGeci.trim();
    }

    public String getMusicDuration() {
        return musicDuration;
    }

    public void setMusicDuration(String musicDuration) {
        this.musicDuration = musicDuration == null ? null : musicDuration.trim();
    }

    public Float getMusicSize() {
        return musicSize;
    }

    public void setMusicSize(Float musicSize) {
        this.musicSize = musicSize;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album == null ? null : album.trim();
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}