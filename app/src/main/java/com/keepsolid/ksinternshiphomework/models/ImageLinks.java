package com.keepsolid.ksinternshiphomework.models;

import android.net.Uri;

import java.util.Objects;

public class ImageLinks {
    private Uri smallThumbnail;
    private Uri thumbnail;

    public ImageLinks(Uri smallThumbnail, Uri thumbnail) {
        this.smallThumbnail = smallThumbnail;
        this.thumbnail = thumbnail;
    }

    public Uri getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(Uri smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public Uri getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Uri thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageLinks that = (ImageLinks) o;
        return Objects.equals(smallThumbnail, that.smallThumbnail) &&
                Objects.equals(thumbnail, that.thumbnail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(smallThumbnail, thumbnail);
    }
}
