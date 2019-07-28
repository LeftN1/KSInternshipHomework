package com.keepsolid.ksinternshiphomework.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookResponse {

    @SerializedName("totalItems")
    private long totalCount;

    @SerializedName("items")
    private List<BookItem> bookItems;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<BookItem> getBookItems() {
        return bookItems;
    }

    public void setBookItems(List<BookItem> bookItems) {
        this.bookItems = bookItems;
    }
}
