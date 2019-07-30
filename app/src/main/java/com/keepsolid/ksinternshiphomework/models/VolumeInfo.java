package com.keepsolid.ksinternshiphomework.models;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VolumeInfo {

    private String title;
    private ArrayList<String> authors;
    private String authorsString; // Это конечно всё костыль, но работает
    private String description;
    private ImageLinks imageLinks;
    private Uri previewLink;

    public VolumeInfo(String tile, ArrayList<String> authors, String description, ImageLinks imageLinks) {
        this.title = tile;
        this.authors = authors;
        this.description = description;
        this.imageLinks = imageLinks;
    }

    public VolumeInfo(String title, String authorsS, String description, String selfUrl, String thumbUrl) {
        this.title = title;
        this.authorsString = authorsS;
        this.description = description;
        this.previewLink = Uri.parse(selfUrl);
        this.imageLinks = new ImageLinks(Uri.EMPTY, Uri.parse(thumbUrl));
    }



    public String getAuthorString(){
        StringBuilder res = new StringBuilder();
        res.append(getAuthors().toString());
        res.replace(0,1,"");
        res.replace(authors.toString().length()-2,authors.toString().length()-1,"");
        this.authorsString = res.toString();
        return authorsString;
    }



    public ArrayList<String> getAuthors() {
        if(authors == null) {
            this.authors = new ArrayList<>();
            this.authors.add("Unknown Author");
        }
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public void setAuthorsString(String s){

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageLinks getImageLinks() {
        if(imageLinks == null){
            this.imageLinks = new ImageLinks(Uri.EMPTY, Uri.EMPTY);
        }
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
