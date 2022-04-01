package com.example.gezgin;

import android.os.Parcel;
import android.os.Parcelable;

public class Places implements Parcelable {

    private String place_name,icon;
    private Double place_lat, place_lng, place_rate;

    public Places(String place_name, String icon, Double place_lat, Double place_lng, Double place_rate) {
        this.place_name = place_name;
        this.icon = icon;
        this.place_lat = place_lat;
        this.place_lng = place_lng;
        this.place_rate = place_rate;
    }

    protected Places(Parcel in) {
        place_name = in.readString();
        icon = in.readString();
        if (in.readByte() == 0) {
            place_lat = null;
        } else {
            place_lat = in.readDouble();
        }
        if (in.readByte() == 0) {
            place_lng = null;
        } else {
            place_lng = in.readDouble();
        }
        if (in.readByte() == 0) {
            place_rate = null;
        } else {
            place_rate = in.readDouble();
        }
    }

    public static final Creator<Places> CREATOR = new Creator<Places>() {
        @Override
        public Places createFromParcel(Parcel in) {
            return new Places(in);
        }

        @Override
        public Places[] newArray(int size) {
            return new Places[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(place_name);
        parcel.writeString(icon);
        if (place_lat == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(place_lat);
        }
        if (place_lng == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(place_lng);
        }
        if (place_rate == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(place_rate);
        }
    }
}
