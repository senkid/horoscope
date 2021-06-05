package com.example.horoscope_app.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Horoscope implements Parcelable {

    private String mLinkHoroscope;
    private String mLinkHoroscopeTomorrow;

    public Horoscope(String LinkHoroscope, String LinkHoroscopeTomorrow) {
        mLinkHoroscope = LinkHoroscope;
        mLinkHoroscopeTomorrow = LinkHoroscopeTomorrow;
    }

    protected Horoscope(Parcel in) {
        String [] data = new String[2];
        in.readStringArray(data);
        mLinkHoroscope = data[0];
        mLinkHoroscopeTomorrow = data[1];
    }

    public static final Creator<Horoscope> CREATOR = new Creator<Horoscope>() {
        @Override
        public Horoscope createFromParcel(Parcel in) {
            return new Horoscope(in);
        }

        @Override
        public Horoscope[] newArray(int size) {
            return new Horoscope[size];
        }
    };

    public String getmLinkHoroscope() {
        return mLinkHoroscope;
    }

    public void setmLinkHoroscope(String mLinkHoroscope) {
        this.mLinkHoroscope = mLinkHoroscope;
    }

    public String getmLinkHoroscopeTomorrow() {
        return mLinkHoroscopeTomorrow;
    }

    public void setmLinkHoroscopeTomorrow(String mLinkHoroscopeTomorrow) {
        this.mLinkHoroscopeTomorrow = mLinkHoroscopeTomorrow;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeStringArray(new String[] {mLinkHoroscope, mLinkHoroscopeTomorrow});
    }
}
