package com.example.weatherviewer;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherData implements Parcelable {

    private String city;
    private int currentTemperature;
    private int highTemperature;
    private int lowTemperature;
    private String description;
    private int tomorrowHigh;
    private int tomorrowLow;
    private String tomorrowDescription;

    public WeatherData(
            String city,
            int currentTemperature,
            int highTemperature,
            int lowTemperature,
            String description,
            int tomorrowHigh,
            int tomorrowLow,
            String tomorrowDescription
    ) {
        this.city = city;
        this.currentTemperature = currentTemperature;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
        this.description = description;
        this.tomorrowHigh = tomorrowHigh;
        this.tomorrowLow = tomorrowLow;
        this.tomorrowDescription = tomorrowDescription;
    }

    private WeatherData(Parcel in) {
        this(
                in.readString(),
                in.readInt(),
                in.readInt(),
                in.readInt(),
                in.readString(),
                in.readInt(),
                in.readInt(),
                in.readString()
        );
    }

    public static final Creator<WeatherData> CREATOR = new Creator<WeatherData>() {
        @Override
        public WeatherData createFromParcel(Parcel in) {
            return new WeatherData(in);
        }

        @Override
        public WeatherData[] newArray(int size) {
            return new WeatherData[size];
        }
    };

    public String getCity() {
        return city;
    }

    public int getCurrentTemperature() {
        return currentTemperature;
    }

    public int getHighTemperature() {
        return highTemperature;
    }

    public int getLowTemperature() {
        return lowTemperature;
    }

    public String getDescription() {
        return description;
    }

    public int getTomorrowHigh(){ return  tomorrowHigh;}

    public int getTomorrowLow(){ return tomorrowLow;}

    public String getTomorrowDescription(){return tomorrowDescription; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getCity());
        parcel.writeInt(getCurrentTemperature());
        parcel.writeInt(getHighTemperature());
        parcel.writeInt(getLowTemperature());
        parcel.writeString(getDescription());
        parcel.writeInt(getTomorrowHigh());
        parcel.writeInt(getTomorrowLow());
        parcel.writeString(getTomorrowDescription());
    }
}






















