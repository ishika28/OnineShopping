package com.ishika.onlineshopping;

import com.ishika.onlineshopping.BL.CartBL;
import com.ishika.onlineshopping.BL.LoginBL;
import com.ishika.onlineshopping.BL.ProductBL;
import com.ishika.onlineshopping.BL.RatingBL;
import com.ishika.onlineshopping.BL.RegisterBL;
import com.ishika.onlineshopping.Model.Product;
import com.ishika.onlineshopping.Model.Rating;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class TechCenterUnitTest {

    //REGISTRATION TEST
    @Test
    public void registerTest() {
        RegisterBL registerBL = new RegisterBL("Test FName", "Test LName", "9841267891", "Test Address", "unittest@gmail.com","*test123","userimg.jpeg","user");
        boolean result = registerBL.addUser();
        assertEquals(true, result);
    }


    //LOGIN TEST
    @Test
    public void loginTest() {
        LoginBL loginBL = new LoginBL("rohandai123@gmail.com", "*rohan123");
        String result = loginBL.checkUser();
        assertNotNull(result);
    }


    //FAILED LOGIN TEST
    @Test
    public void failedLoginTest() {
        LoginBL loginBL = new LoginBL("rohandai123@gmail.com", "wrong");
        String result = loginBL.checkUser();
        assertEquals("Invalid",result);
    }

    //VIEW PRODUCTS TEST
    @Test
    public void viewProductsTest()
    {
        ProductBL productBL = new ProductBL();
        List<Product> result =productBL.getProduct();
        String productName = result.get(0).getProduct_name();
        assertEquals("Razer DeathAdder Elite",productName);

    }


    // ADD TO CART TEST
    @Test
    public void  addItemToCartTest()
    {
        CartBL cartBL = new CartBL("rohandai123@gmail.com","Razer DeathAdder Elite","75","1","imageFile-1576149573474.jpg");
        boolean result = cartBL.addToCart();
        assertEquals(true,result);
    }


    // RATE PRODUCTS TEST
    @Test
    public void ratingTest()
    {
        Rating rating = new Rating("rohandai123@gmail.com","Razer Blackwidow Chroma","5.0");
        RatingBL  ratingBL = new RatingBL();
        String result = ratingBL.addRating(rating);
        assertEquals("Success",result);
    }

    // VIEW RATING TEST
    @Test
    public void viewRatingTest()
    {
        Rating rating = new Rating("rohandai123@gmail.com","Razer DeathAdder Elite");
        RatingBL ratingBL = new RatingBL();
        String result = ratingBL.getMyRating(rating).get(0).getRating();
        assertEquals("4.0",result);
    }

    // UPDATE RATING TEST
    @Test
    public void updateRating(){
        String id="5df223c6ddd9ac374415f468";
        Float newRating = 4.5f;
        Rating rating = new Rating(id,newRating);
        RatingBL ratingBL = new RatingBL();

        String message=ratingBL.updateRating(id,rating);
        assertThat(message,is("Rating Updated Successfully"));
    }


    // GET CATEGORY TEST
    @Test
    public void getCategoryTest(){
        ProductBL productBL = new ProductBL();
        int category = productBL.getCategory().size();
        assertNotEquals(0,category);
    }

}
