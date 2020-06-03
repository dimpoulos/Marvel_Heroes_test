package com.codehub.marvelheroesapp.DDatabase.Local;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.codehub.marvelheroesapp.DDatabase.ModelDB.Cart;
import com.codehub.marvelheroesapp.DDatabase.ModelDB.Favorite;

@Database(entities = {Cart.class, Favorite.class},version = 1 )
public abstract class CartDatabase extends RoomDatabase {
    public abstract CartDAO cartDAO();
    public abstract FavoriteDAO favoriteDAO();

    private static CartDatabase instance;

    public static CartDatabase getInstance(Context context){

        if(instance == null)
            instance = Room.databaseBuilder(context, CartDatabase.class,"EDMT_HeroAppDB")
                    .allowMainThreadQueries()
                    .build();
        return instance;
    }


}
