package com.codehub.marvelheroesapp.DDatabase.ModelDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.codehub.marvelheroesapp.json.ImageModel;


@Entity (tableName = "Cart")
public class Cart {



        @NonNull
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name="id")
        public int id;

        @ColumnInfo(name="name")
        public String name;

        @ColumnInfo(name="desc")
        public String desc;





}
