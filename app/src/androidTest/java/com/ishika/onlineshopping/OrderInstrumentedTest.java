package com.ishika.onlineshopping;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.ishika.onlineshopping.Activity.MainActivity;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsNot;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class OrderInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> orderTest = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void TestUI() throws Exception {

        onView(ViewMatchers.withId(R.id.action_cart))
                .perform(ViewActions.click());

        onView(withId(R.id.scrollViewCart)).perform(swipeUp());

        onView(ViewMatchers.withId(R.id.cart_btn_checkout))
                .perform(ViewActions.click());

        onView(allOf(withId(R.id.btnCashAtDelivery),withText("PAY AT DELIVERY"))).perform(click());


        onView(ViewMatchers.withText("Order Placed Successfully"))
                .inRoot(RootMatchers.withDecorView(IsNot.not(Matchers.is(orderTest.getActivity().getWindow().getDecorView()))))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        orderTest.finishActivity();
    }
}