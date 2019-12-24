package com.ufs.campaign.fda.domain;

import com.ufs.campaign.annotation.CsvField;

public class Demographic {
    @CsvField(name = "aid")
    private String aid;
    @CsvField(name = "wechatFlag")
    private String wechatFlag;
    @CsvField(name = "restaurant_province")
    private String restaurant_province;
    @CsvField(name = "restaurant_city_level")
    private String restaurant_city_level;

    public String getWechatFlag() {
        return wechatFlag;
    }

    public void setWechatFlag(String wechatFlag) {
        this.wechatFlag = wechatFlag;
    }

    public String getRestaurant_province() {
        return restaurant_province;
    }

    public void setRestaurant_province(String restaurant_province) {
        this.restaurant_province = restaurant_province;
    }

    public String getRestaurant_city_level() {
        return restaurant_city_level;
    }

    public void setRestaurant_city_level(String restaurant_city_level) {
        this.restaurant_city_level = restaurant_city_level;
    }

    public String getRestaurant_county() {
        return restaurant_county;
    }

    public void setRestaurant_county(String restaurant_county) {
        this.restaurant_county = restaurant_county;
    }

    private String restaurant_county;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

}
