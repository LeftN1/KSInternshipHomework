package com.keepsolid.ksinternshiphomework.api;

import com.keepsolid.ksinternshiphomework.models.BookResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/books/v1/volumes")
    Call<BookResponse> getBooks(@Query("q") String query, @Query("maxResults") int i);


}