package com.ishika.onlineshopping.Interface;

import com.ishika.onlineshopping.Model.AuthUser;
import com.ishika.onlineshopping.Model.Cart;
import com.ishika.onlineshopping.Model.CartMessageModel;
import com.ishika.onlineshopping.Model.Category;
import com.ishika.onlineshopping.Model.OrderHistory;
import com.ishika.onlineshopping.Model.Product;
import com.ishika.onlineshopping.Model.Rating;
import com.ishika.onlineshopping.Model.Register;
import com.ishika.onlineshopping.Model.Shop;
import com.ishika.onlineshopping.Model.TotalRatingModel;
import com.ishika.onlineshopping.Model.User;
import com.ishika.onlineshopping.Model.UserImg;
import com.ishika.onlineshopping.Model.Wishlist;
import com.ishika.onlineshopping.Model.WishlistMessageModel;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Api {

    @POST("users/register")
    Call<Register> addUser(@Body User user);

    @POST ("users/login")
    Call<AuthUser> getUser(@Body User user);

    @GET("users/me")
    Call<User> getProfile(@Header("Authorization") String token);

    @Multipart
    @POST("users/userImageUpload")
    Call<UserImg> uploadImage (@Part MultipartBody.Part body);

    @PUT("users/updateUser/{email}")
    Call<Boolean> updateUser(@Path("email") String email, @Body User user);

    @GET("products/products")
    Call<List<Product>> getProduct();

    @POST("ratings")
    Call<Register> addRating(@Body Rating rating);

    @POST("ratings/myRating")
    Call<List<Rating>> getMyRating(@Body Rating rating);

    @PUT("ratings/{id}")
    Call<Register> updateRating(@Path("id") String id, @Body Rating rating);

    @GET("ratings/totalrating/{product_name}")
    Call<TotalRatingModel> getTotalRating(@Path("product_name") String productName);

    @POST("carts")
    Call<CartMessageModel> addToCart(@Body Cart cart);

    @GET("carts/{email}")
    Call<List<Cart>> getCart(@Path("email") String email);

    @DELETE("carts/removefromcart/{id}")
    Call<Void> deleteCartRow(@Path("id") String id);

    @POST("orders/multiple")
    Call<CartMessageModel> addOrder(@Body Cart cart);

    @GET("orders/{email}")
    Call<List<OrderHistory>> getOrderHistory(@Path("email") String email);

    @POST("wishlists")
    Call<WishlistMessageModel> addToWishlist(@Body Wishlist wishlist);

    @GET("wishlists/{email}")
    Call<List<Wishlist>> getWishlist(@Path("email") String email);

    @DELETE("wishlists/removefromwishlist/{id}")
    Call<Void> deleteWishlistRow(@Path("id") String id);

    @GET("shops/shop")
    Call<List<Shop>> getShops();

    @GET("categories")
    Call<List<Category>> getCategories();

    @GET("products/product/{category}")
    Call<List<Product>> getProductByCategory(@Path("category") String category);

    @POST("users/logout")
    Call<Register> logout(@Header("Authorization") String token);

}
