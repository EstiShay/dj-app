package com.epicodus.djmusicmanager.services;

import android.util.Log;

import com.epicodus.djmusicmanager.Constants;
import com.epicodus.djmusicmanager.models.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MusixService {

    public static void findSongs(String songTitle, String artistName, Callback callback){

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MUSIX_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("apikey", Constants.MUSIX_KEY);
        urlBuilder.addQueryParameter(Constants.MUSIX_TITLE_QUERY_PARAMETER, songTitle);
        urlBuilder.addQueryParameter("page_size", "100");
        if (!artistName.equals("")){
            urlBuilder.addQueryParameter(Constants.MUSIX_ARTIST_QUERY_PARAMETER, artistName);
        }
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Song> processResults(Response response){
        ArrayList<Song> songs = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject musixJSON = new JSONObject(jsonData);
            JSONObject messageJSON = musixJSON.getJSONObject("message");
            JSONObject bodyJSON = messageJSON.getJSONObject("body");
            JSONArray tracksJSON = bodyJSON.getJSONArray("track_list");
            for (int i = 0; i <tracksJSON.length(); i++){
                JSONObject songJSON = tracksJSON.getJSONObject(i);
                JSONObject trackJSON = songJSON.getJSONObject("track");
                String title = trackJSON.optString("track_name", "Track name missing");
                String artist = trackJSON.optString("artist_name", "Artist name missing");
                String album = trackJSON.optString("album_name", "Album name missing");
                String release = trackJSON.optString("first_release_date");
                String year = "";
                if (release.length() > 3) {
                    year = release.substring(0,4);
                }
                Song song = new Song(title, artist, album, year);
                songs.add(song);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return songs;
    }
}
