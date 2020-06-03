package com.codehub.marvelheroesapp.DDatabase.DataSource;

import com.codehub.marvelheroesapp.DDatabase.ModelDB.Cart;

import java.util.List;

import io.reactivex.Flowable;

public interface IcartDataSource {
    Flowable<List<Cart>> getCartItems();
    Flowable<List<Cart>>getItemById(int cartItemId);
    int countCartItems();
    void emptyCart();
    void insertToCart(Cart... carts);
    void updateCart(Cart... carts);
    void deleteCartItem(Cart cart);
}
