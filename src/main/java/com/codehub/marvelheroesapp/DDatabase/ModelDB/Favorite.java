package com.codehub.marvelheroesapp.DDatabase.ModelDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Favorite")
public class Favorite {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name="id")
    public Integer id;

    @ColumnInfo(name="name")
    public String name;

    @ColumnInfo(name="link")
    public String link;

    @ColumnInfo(name="menuId")
    public String menuId;


}
