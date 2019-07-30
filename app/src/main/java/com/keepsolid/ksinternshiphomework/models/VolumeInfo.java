package com.keepsolid.ksinternshiphomework.models;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VolumeInfo {

    private String title;
    private ArrayList<String> authors;
    private String description;
    private ImageLinks imageLinks;
    private Uri previewLink;

    public VolumeInfo(String tile, ArrayList<String> authors, String description, ImageLinks imageLinks) {
        this.title = tile;
        this.authors = authors;
        this.description = description;
        this.imageLinks = imageLinks;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public String getAuthorString(){
        StringBuilder res = new StringBuilder();
        res.append(authors.toString());
        res.replace(0,1,"");
        res.replace(authors.toString().length()-2,authors.toString().length()-1,"");
        return res.toString();
    }

    public String getAuthorStringTable(){
        StringBuilder auth = new StringBuilder();
        for (String s : authors){
            auth.append(s);
            auth.append(", ");
        }
        auth.replace(auth.length()-2, auth.length()-1,"");
        String res = auth.toString();
        return res;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Uri getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(Uri previewLink) {
        this.previewLink = previewLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolumeInfo that = (VolumeInfo) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(authors, that.authors) &&
                Objects.equals(description, that.description) &&
                Objects.equals(imageLinks, that.imageLinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authors, description, imageLinks);
    }
}
