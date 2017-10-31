package com.epicodus.djmusicmanager.models;

import org.parceler.Parcel;

@Parcel
public class Record {

    String typeDance;
    String recTitle;
    String recArtist;
    String recAlbum;
    String recYear;
    String beforeSong;
    String afterSong;
    String bpm;
    String owned;
    private String pushId;

    public Record() {}

    public Record(String typeDance, String recTitle, String recArtist, String recAlbum, String recYear, String beforeSong, String afterSong, String bpm, String owned){
        this.typeDance = typeDance;
        this.recTitle = recTitle;
        this.recArtist = recArtist;
        this.recAlbum = recAlbum;
        this.recYear = recYear;
        this.beforeSong = beforeSong;
        this.afterSong = afterSong;
        this.bpm = bpm;
        this.owned = owned;
    }

    public String getTypeDance(){
        return typeDance;
    }

    public String getRecTitle(){
        return recTitle;
    }

    public String getRecArtist(){
        return recArtist;
    }

    public String getRecAlbum(){
        return recAlbum;
    }

    public String getRecYear(){
        return recYear;
    }

    public String getBeforeSong(){
        return beforeSong;
    }

    public String getAfterSong(){
        return afterSong;
    }

    public String getBpm(){
        return bpm;
    }

    public String getOwned(){
        return owned;
    }

    public String getPushId(){
        return pushId;
    }

    public void setPushId(String pushId){
        this.pushId = pushId;
    }
}