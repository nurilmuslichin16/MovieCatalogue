package com.example.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalogue.R
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.EspressoIdlingResources
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest{
    private val dummyMovie = DataDummy.generateITestDummyMovie()
    private val dummyTv = DataDummy.generateITestDummyTv()

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResources.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.espressoIdlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadTv() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTv.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.iv_image_detail)).check(matches(isDisplayed()))

        onView(withId(R.id.iv_back_image_detail)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovie[0].title)))

        onView(withId(R.id.tv_category)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_category)).check(matches(withText(dummyMovie[0].category)))

        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyMovie[0].overview)))

        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(withText(dummyMovie[0].release)))
    }

    @Test
    fun loadDetailTv() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.iv_image_detail)).check(matches(isDisplayed()))

        onView(withId(R.id.iv_back_image_detail)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyTv[0].title)))

        onView(withId(R.id.tv_category)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_category)).check(matches(withText(dummyTv[0].category)))

        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyTv[0].overview)))

        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(withText(dummyTv[0].release)))
    }

    @Test
    fun loadDetailFavoriteMovie() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.fab_add_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.menu_favorite)).perform(click())
        onView(withId(R.id.rv_movies_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.iv_image_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_back_image_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_category)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))

        onView(withId(R.id.fab_add_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadDetailFavoriteTv() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.fab_add_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.menu_favorite)).perform(click())
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tv_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.iv_image_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_back_image_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_category)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))

        onView(withId(R.id.fab_add_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }
}