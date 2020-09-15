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
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class AddToCartInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> addToCartTest = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void TestUI() throws Exception {
        onView(withId(R.id.action_product)).perform(click());

        onView(withId(R.id.recyclerViewProducts)).perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.scrollViewProduct)).perform(swipeUp());
        onView(Matchers.allOf(ViewMatchers.withId(R.id.btn_cart), ViewMatchers.withText("Add to Cart")))
                .perform(ViewActions.click());

        onView(ViewMatchers.withText("Added to Cart!"))
                .inRoot(RootMatchers.withDecorView(IsNot.not(Matchers.is(addToCartTest.getActivity().getWindow().getDecorView()))))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        addToCartTest.finishActivity();
    }
}
