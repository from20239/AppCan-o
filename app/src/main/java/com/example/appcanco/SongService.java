package com.example.appcanco;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SongService {

    @GET("tracks")
    Call<List<Song>> getSongs();

    @PUT("tracks/{id}")
    Call<Void> modifySong(@Path("id") String id, @Body Song song);

    @DELETE("tracks/{id}")
    Call<Void> deleteSong(@Path("id") String id);

    @POST("tracks")
    Call<Void> addSong(@Body Song song);
}