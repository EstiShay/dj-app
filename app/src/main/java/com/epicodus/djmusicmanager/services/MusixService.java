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
        Log.d("service", "fired");

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MUSIX_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("apikey", Constants.MUSIX_KEY);
        urlBuilder.addQueryParameter(Constants.MUSIX_TITLE_QUERY_PARAMETER, songTitle);
        if (!artistName.equals("")){
            urlBuilder.addQueryParameter(Constants.MUSIX_ARTIST_QUERY_PARAMETER, artistName);
        }
        String url = urlBuilder.build().toString();
        Log.d("url", url);

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
            JSONArray tracksJSON = musixJSON.getJSONArray("track_list");
            for (int i = 0; i <tracksJSON.length(); i++){
                JSONObject songJSON = tracksJSON.getJSONObject(i);
                String title = songJSON.getString("track_name");
                String artist = songJSON.getString("artist_name");
                String album = songJSON.getString("album_name");
                String year = songJSON.getString("first_release_date").substring(0,3);
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
