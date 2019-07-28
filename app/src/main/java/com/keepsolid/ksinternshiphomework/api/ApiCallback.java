package com.keepsolid.ksinternshiphomework.api;


import com.keepsolid.ksinternshiphomework.models.BookErrorItem;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public abstract class ApiCallback<T> implements Callback<T> {

    public abstract void success(Response<T> response);

    public abstract void failure(BookErrorItem gitRepoError);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (!response.isSuccessful()) {
            Converter<ResponseBody, BookErrorItem> converter = RestClient.getInstance().getRetrofit().responseBodyConverter(BookErrorItem.class, new Annotation[0]);

            try {
                BookErrorItem repoError = converter.convert(response.errorBody());
                failure(repoError);
            } catch (Exception e) {
                failure(new BookErrorItem("Unhandled error! Code: " + response.code()));
            }
        } else {
            success(response);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        failure(new BookErrorItem("Unexpected error! Info: " + t.getMessage()));
    }
}
