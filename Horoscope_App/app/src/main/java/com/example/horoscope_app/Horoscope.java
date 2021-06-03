package com.example.horoscope_app;

import android.os.Parcel;
import android.os.Parcelable;

public class Horoscope implements Parcelable {


    private String mDescription;
    private String mBusines;
    private String mLove;
    private String mNumeral;

    public Horoscope(String mDescription, String mBusines, String mLove, String mNumeral) {
        this.mDescription = mDescription;
        this.mBusines = mBusines;
        this.mLove = mLove;
        this.mNumeral = mNumeral;
    }

    protected Horoscope(Parcel in) // конструктор позволяющий извлечь переменные
    {
        String [] data = new String[4];
        in.readStringArray(data);
        mDescription = data[0];
        mBusines = data[1];
        mLove = data[2];
        mNumeral = data[3];
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

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmBusines() {
        return mBusines;
    }

    public void setmBusines(String mBusines) {
        this.mBusines = mBusines;
    }

    public String getmLove() {
        return mLove;
    }

    public void setmLove(String mLove) {
        this.mLove = mLove;
    }

    public String getmNumeral() {
        return mNumeral;
    }

    public void setmNumeral(String mNumeral) {
        this.mNumeral = mNumeral;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeStringArray(new String[] {mDescription, mBusines, mLove, mNumeral});

    }



}
