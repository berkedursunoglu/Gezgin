package com.example.gezgin;

import android.os.Parcel;
import android.os.Parcelable;

public class Places {

    private String place_name,icon;
    private Double place_lat, place_lng, place_rate;

    public Places(String place_name, String icon, Double place_lat, Double place_lng, Double place_rate) {
        this.place_name = place_name;
        this.icon = icon;
        this.place_lat = place_lat;
        this.place_lng = place_lng;
        this.place_rate = place_rate;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Double getPlace_lat() {
        return place_lat;
    }

    public void setPlace_lat(Double place_lat) {
        this.place_lat = place_lat;
    }

    public Double getPlace_lng() {
        return place_lng;
    }

    public void setPlace_lng(Double place_lng) {
        this.place_lng = place_lng;
    }

    public Double getPlace_rate() {
        return place_rate;
    }

    public void setPlace_rate(Double place_rate) {
        this.place_rate = place_rate;
    }
}
