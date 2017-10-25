package com.epicodus.djmusicmanager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class MusixService {

    public static void findSongs(String songTitle, String artistName, Callback callback){

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MUSIX_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.MUSIX_TITLE_QUERY_PARAMETER, songTitle);
        if (artistName != ""){
            urlBuilder.addQueryParameter(Constants.MUSIX_ARTIST_QUERY_PARAMETER, artistName);
        }
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("apikey", Constants.MUSIX_KEY)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }
}
