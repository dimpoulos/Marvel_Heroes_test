package com.codehub.marvelheroesapp.DDatabase.DataSource;

import androidx.room.Delete;
import androidx.room.Query;

import com.codehub.marvelheroesapp.DDatabase.ModelDB.Favorite;

import java.util.List;

import io.reactivex.Flowable;

public interface IFavoriteDataSource {


    Flowable<List<Favorite>> getFavItems();

    int isFavorite(int itemId);

    void insertFav(Favorite...favorites);

    void delete(Favorite favorite);
}
