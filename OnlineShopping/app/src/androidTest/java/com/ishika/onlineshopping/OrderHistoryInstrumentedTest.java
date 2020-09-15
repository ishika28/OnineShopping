package com.ishika.onlineshopping;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.ishika.onlineshopping.Activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class OrderHistoryInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> orderTest = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void TestUI() throws Exception {

        onView(withId(R.id.action_profile)).perform(click());

        onView(withId(R.id.btn_viewHistory)).perform(click());

        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewOrderHistory))
                .perform(ViewActions.click());

        orderTest.finishActivity();
    }
}
