
package com.example.gezgin;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Place {

    @SerializedName("places_id")
    @Expose
    private String placesId;
    @SerializedName("places_name")
    @Expose
    private String placesName;
    @SerializedName("places_img")
    @Expose
    private String placesImg;
    @SerializedName("places_rate")
    @Expose
    private String placesRate;

    public String getPlacesId() {
        return placesId;
    }

    public void setPlacesId(String placesId) {
        this.placesId = placesId;
    }

    public String getPlacesName() {
        return placesName;
    }

    public void setPlacesName(String placesName) {
        this.placesName = placesName;
    }

    public String getPlacesImg() {
        return placesImg;
    }

    public void setPlacesImg(String placesImg) {
        this.placesImg = placesImg;
    }

    public String getPlacesRate() {
        return placesRate;
    }

    public void setPlacesRate(String placesRate) {
        this.placesRate = placesRate;
    }

}
