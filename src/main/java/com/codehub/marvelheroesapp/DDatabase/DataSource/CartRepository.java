package com.codehub.marvelheroesapp.DDatabase.DataSource;

import com.codehub.marvelheroesapp.DDatabase.ModelDB.Cart;

import java.util.List;

import io.reactivex.Flowable;

public class CartRepository implements IcartDataSource {

    private IcartDataSource icartDataSource;

    public CartRepository(IcartDataSource icartDataSource) {
        this.icartDataSource = icartDataSource;
    }

    private static CartRepository instance;

    public static CartRepository getInstance(IcartDataSource icartDataSource){

        if(instance == null)
            icartDataSource = new CartRepository(icartDataSource);
        return instance;
    }

    @Override
    public Flowable<List<Cart>> getCartItems() {
        return icartDataSource.getCartItems();
    }

    @Override
    public Flowable<List<Cart>> getItemById(int cartItemId) {
        return icartDataSource.getItemById(cartItemId);
    }

    @Override
    public int countCartItems() {
        return icartDataSource.countCartItems();
    }

    @Override
    public void emptyCart() {
        icartDataSource.emptyCart();

    }

    @Override
    public void insertToCart(Cart... carts) {
        icartDataSource.insertToCart(carts);

    }

    @Override
    public void updateCart(Cart... carts) {
        icartDataSource.updateCart(carts);

    }

    @Override
    public void deleteCartItem(Cart cart) {
        icartDataSource.deleteCartItem(cart);

    }
}
