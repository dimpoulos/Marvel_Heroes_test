package com.codehub.marvelheroesapp.json;

import androidx.annotation.Nullable;

public class HeroesModel {



    private Integer id;
    private String name;
    private String description;
    private ImageModel thumbnail;
    private String favStatus;

    public HeroesModel(Integer id, String name, String description, ImageModel thumbnail, String favStatus) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
    }


    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Nullable
    public ImageModel getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ImageModel thumbnail) {
        this.thumbnail = thumbnail;
    }
    public String getFavStatus(){
        return favStatus;
    }



    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }
}
