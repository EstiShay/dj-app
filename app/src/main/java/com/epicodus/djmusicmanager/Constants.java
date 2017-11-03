package com.epicodus.djmusicmanager;

public class Constants {
    public static final String MUSIX_KEY = BuildConfig.MUSIX_KEY;
    public static final String MUSIX_BASE_URL = "https://api.musixmatch.com/ws/1.1/track.search?";
    public static final String MUSIX_TITLE_QUERY_PARAMETER = "q_track";
    public static final String MUSIX_ARTIST_QUERY_PARAMETER = "q_artist";

    public static final String PREFERENCES_TITLE_KEY = "songTitle";
    public static final String PREFERENCES_LOGIN_EMAIL = "email";

    public static final String FIREBASE_CHILD_SONGS = "songs";

    public static final String FIREBASE_QUERY_INDEX = "index";
}
