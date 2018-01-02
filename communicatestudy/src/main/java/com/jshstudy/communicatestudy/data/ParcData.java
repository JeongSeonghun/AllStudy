package com.jshstudy.communicatestudy.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by EMGRAM on 2018-01-02.
 */

public class ParcData implements Parcelable{
    /*
    optimal IPC(Inter Process Communication)
    faster serialization cause Parcelable change from user
    however complex implement
    should write order equal read order
     */
    // mandatory method constructor(parcel in), creator, describeContents(), writeToParcel
    protected ParcData(Parcel in) {
        setPNum(in.readInt());
        setPStr(in.readString());
    }

    // when unmarshalling
    public static final Creator<ParcData> CREATOR = new Creator<ParcData>() {
        // parcel data -> origin data
        @Override
        public ParcData createFromParcel(Parcel in) {
            return new ParcData(in);
        }

        @Override
        public ParcData[] newArray(int size) {
            return new ParcData[size];
        }
    };

    // when use FileDescriptor and etc...
    @Override
    public int describeContents() {
        return 0;
    }

    // when marshalling
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pNum);
        dest.writeString(pStr);
    }

    private int pNum;
    private String pStr;

    public ParcData(){}

    public void setPNum(int pNum){
        this.pNum = pNum;
    }

    public int getPNum(){
        return pNum;
    }

    public void setPStr(String pStr){
        this.pStr = pStr;
    }

    public String getPStr(){
        return pStr;
    }

    @Override
    public String toString() {
        return "{num : "+pNum+", str : '"+pStr+"'}";
    }
}
