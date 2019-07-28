package com.keepsolid.ksinternshiphomework.models;

import android.net.Uri;

import java.util.List;
import java.util.Objects;

public class BookItem {
    private String id;
    private VolumeInfo volumeInfo;

    public BookItem(String id, VolumeInfo volumeInfo, Uri selfLink) {
        this.id = id;
        this.volumeInfo = volumeInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookItem bookItem = (BookItem) o;
        return Objects.equals(id, bookItem.id) &&
                Objects.equals(volumeInfo, bookItem.volumeInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
