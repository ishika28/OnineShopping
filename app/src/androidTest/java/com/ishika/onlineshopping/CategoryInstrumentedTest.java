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
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class CategoryInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> categoryTest = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void TestUI() throws Exception {
        onView(withText("View Our Categories")).check(matches(isDisplayed()));

        onView(allOf(withText("Mouse"))).perform(click());

        onView(withText("Razer DeathAdder Elite")).check(matches(isDisplayed()));

        categoryTest.finishActivity();
    }
}
