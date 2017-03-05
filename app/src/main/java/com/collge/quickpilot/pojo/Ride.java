package com.collge.quickpilot.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Siddhesh on 20-01-2017.
 */

public class Ride implements Parcelable{

    String myMobile, rikMobile, sourceLat, sourceLng, destinationLat, destinationLng, rikLat, rikLng;
    int status;

    /**
     * status
     * 0 searching
     * 1 waiting
     * 2 confirm
     * 3 discard
     */
    int SEARCHING = 0, WAITING = 1, CONFIRM = 2, COMPLETED = 3;

    Ride(){}

    public Ride(String myMobile, String sourceLat, String sourceLng, String destinationLat, String destinationLng) {
        this.myMobile = myMobile;
        this.sourceLat = sourceLat;
        this.sourceLng = sourceLng;
        this.destinationLat = destinationLat;
        this.destinationLng = destinationLng;
        this.status = SEARCHING;
    }

    private Ride(Parcel in) {

        status = in.readInt();
        myMobile = in.readString();
        rikMobile = in.readString();
        sourceLat = in.readString();
        sourceLng = in.readString();
        destinationLat = in.readString();
        destinationLng = in.readString();
        rikLat = in.readString();
        rikLng = in.readString();

    }

    public static final Parcelable.Creator<Ride> CREATOR = new Parcelable.Creator<Ride>() {
        @Override
        public Ride createFromParcel(Parcel in) {
            return new Ride(in);
        }

        @Override
        public Ride[] newArray(int size) {
            return new Ride[size];
        }
    };

    public String getMyMobile() {
        return this.myMobile;
    }

    public void setMyMobile(String myMobile) {
        this.myMobile = myMobile;
    }

    public String getRikMobile() {
        return rikMobile;
    }

    public void setRikMobile(String rikMobile) {
        this.rikMobile = rikMobile;
    }

    public String getSourceLat() {
        return sourceLat;
    }

    public void setSourceLat(String sourceLat) {
        this.sourceLat = sourceLat;
    }

    public String getSourceLng() {
        return sourceLng;
    }

    public void setSourceLng(String sourceLng) {
        this.sourceLng = sourceLng;
    }

    public String getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(String destinationLat) {
        this.destinationLat = destinationLat;
    }

    public String getDestinationLng() {
        return destinationLng;
    }

    public void setDestinationLng(String destinationLng) {
        this.destinationLng = destinationLng;
    }

    public String getRikLat() {
        return rikLat;
    }

    public void setRikLat(String rikLat) {
        this.rikLat = rikLat;
    }

    public String getRikLng() {
        return rikLng;
    }

    public void setRikLng(String rikLng) {
        this.rikLng = rikLng;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(status);
        dest.writeString(myMobile);
        dest.writeString(rikMobile);
        dest.writeString(sourceLat);
        dest.writeString(sourceLng);
        dest.writeString(destinationLat);
        dest.writeString(destinationLng);
        dest.writeString(rikLat);
        dest.writeString(rikLng);
    }
}
