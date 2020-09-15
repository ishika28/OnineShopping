package com.ishika.onlineshopping;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.ishika.onlineshopping.Activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class LogoutInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> logoutTest = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void TestUI() throws Exception {

        onView(withId(R.id.action_profile)).perform(click());

        onView(withId(R.id.btn_logout)).perform(click());

        onView(withText("Yes"))
                .inRoot(isDialog()).
                check(matches(isDisplayed())).perform(click());

        onView(withText("Logout successful"))
                .inRoot(withDecorView(not(is(logoutTest.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

        logoutTest.finishActivity();
    }
}
