package com.ishika.onlineshopping;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.ishika.onlineshopping.Activity.RegisterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class RegisterInstrumentedTest {

    @Rule
    public ActivityTestRule<RegisterActivity> registerTest=
            new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void TestUI() throws Exception
    {
        onView(withId(R.id.ra_firstName))
                .perform(typeText("Rohan"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.ra_lastName))
                .perform(typeText("Maharjan"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.ra_phone))
                .perform(typeText("9860304670"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.ra_address))
                .perform(typeText("Kuleshwor"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.ra_email))
                .perform(typeText("rohan123@gmail.com"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.ra_password))
                .perform(typeText("*rohan123"))
                .perform(closeSoftKeyboard());


        onView(withId(R.id.ra_btnRegister))
                .perform(click());

    }
}