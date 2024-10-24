package com.example.appcanco;

public class Song {
    private String id;
    private String singer;
    private String title;

    public Song(String id, String singer, String title) {
        this.id = id;
        this.singer = singer;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getSinger() {
        return singer;
    }

    public String getTitle() {
        return title;
    }
}