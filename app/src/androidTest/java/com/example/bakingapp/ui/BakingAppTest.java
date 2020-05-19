package com.example.bakingapp.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.bakingapp.R;
import com.example.bakingapp.util.Utils;
import com.google.android.exoplayer2.ui.PlayerView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class BakingAppTest {

    @NonNull
    private final Utils utils = new Utils();
    private CountingIdlingResource idlingResource;

    @Rule
    public final ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void registerIdlingResource() {
        idlingResource = utils.getAppContainer(Objects.requireNonNull(activityTestRule.getActivity())
                .getApplication()).getCountingIdlingResource();
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    public void perform_click_on_first_recipe_using_idling_resource() {
        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition(0, click()));
    }

    @Test
    public void verify_provided_json_has_four_items() {
        // Assuming provided json still only has 4 results: https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json
        onView(withId(R.id.recyclerView)).check(matches(hasChildCount(4)));
    }

    @Test
    public void verify_details_screen_has_ingredients_list() {
        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition(0, click()));

        onView(allOf(
                withId(R.id.ingredients),
                isAssignableFrom(RecyclerView.class)
        )).check(matches(isDisplayed()));
    }

    @Test
    public void verify_details_screen_has_steps_list() {
        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition(0, click()));

        onView(allOf(
                withId(R.id.steps),
                isAssignableFrom(RecyclerView.class)
        )).check(matches(isDisplayed()));
    }

    @Test
    public void perform_click_on_first_recipe_step() {
        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition(0, click()));

        onView(allOf(
                withId(R.id.steps),
                isAssignableFrom(RecyclerView.class)
        )).perform(actionOnItemAtPosition(0, click()));
    }

    @Test
    public void verify_steps_screen_has_player() {
        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition(0, click()));

        onView(allOf(
                withId(R.id.steps),
                isAssignableFrom(RecyclerView.class)
        )).perform(actionOnItemAtPosition(0, click()));

        onView(allOf(
                withId(R.id.player),
                withClassName(is(PlayerView.class.getName()))
        )).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
    }
}