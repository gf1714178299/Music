package com.gf.musics.web.model;

public class Singer {
    private Integer pkId;

    private String singerName;

    private String singerImg;

    private Integer singerGender;

    private Integer singerStyleid;

    private Integer countryType;

    private String description;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName == null ? null : singerName.trim();
    }

    public String getSingerImg() {
        return singerImg;
    }

    public void setSingerImg(String singerImg) {
        this.singerImg = singerImg == null ? null : singerImg.trim();
    }

    public Integer getSingerGender() {
        return singerGender;
    }

    public void setSingerGender(Integer singerGender) {
        this.singerGender = singerGender;
    }

    public Integer getSingerStyleid() {
        return singerStyleid;
    }

    public void setSingerStyleid(Integer singerStyleid) {
        this.singerStyleid = singerStyleid;
    }

    public Integer getCountryType() {
        return countryType;
    }

    public void setCountryType(Integer countryType) {
        this.countryType = countryType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}