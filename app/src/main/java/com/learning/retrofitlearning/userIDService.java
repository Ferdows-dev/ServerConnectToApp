package com.learning.retrofitlearning;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface userIDService {
    @GET("posts")
    Call<List<posts>> getAllPosts();
}
