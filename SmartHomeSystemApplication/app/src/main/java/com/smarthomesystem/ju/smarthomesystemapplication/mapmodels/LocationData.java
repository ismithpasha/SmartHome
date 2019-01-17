package com.smarthomesystem.ju.smarthomesystemapplication.mapmodels;

/**
 * Created by ismith-fsl on 8/2/2017.
 */

public class LocationData {

    public LocationData() {

    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSnippet() {
        return Snippet;
    }

    public void setSnippet(String snippet) {
        Snippet = snippet;
    }

    public double Latitude;
    public double Longitude;
    public String Title;
    public String Snippet;

    public LocationData(double latitude, double longitude, String title, String snippet) {
        Latitude = latitude;
        Longitude = longitude;
        Title = title;
        Snippet = snippet;
    }
}
