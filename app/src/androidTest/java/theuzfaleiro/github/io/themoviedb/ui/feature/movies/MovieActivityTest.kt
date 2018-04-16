package theuzfaleiro.github.io.themoviedb.ui.feature.movies

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import theuzfaleiro.github.io.themoviedb.R
import theuzfaleiro.github.io.themoviedb.data.model.movie.Movie
import theuzfaleiro.github.io.themoviedb.ui.feature.detail.MovieDetailActivity


class MovieActivityTest {

    @get:Rule
    val movieActivityTest = IntentsTestRule(MovieActivity::class.java, true, false)

    @Before
    fun setUp() {
        RESTMockServer.reset()
    }

    @Test
    fun shouldShowMovieList_WhenFetchedMovieDataFromAPI() {
        RESTMockServer.whenGET(RequestMatchers.pathContains("movie/upcoming")).thenReturnFile(200, "movie/movie-list.json")

        movieActivityTest.launchActivity(Intent())

        onView(withId(R.id.recyclerViewMovie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
    }

    @Test
    fun shouldShowErrorMessage_WhenNoneMovieDataWasFetchedMovieFromAPI() {
        RESTMockServer.whenGET(RequestMatchers.pathContains("movie/upcoming")).thenReturnEmpty(404)

        movieActivityTest.launchActivity(Intent())

        onView(withId(R.id.imageViewConnectionError)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewSomethingWentWrong)).check(matches(withText(R.string.activity_movie_error_please_try_again)))
    }


    @Test
    fun shouldShowReloadMovieData_WhenTryAgainButtonWasClicked() {
        RESTMockServer.whenGET(RequestMatchers.pathContains("movie/upcoming")).thenReturnEmpty(404).thenReturnFile(200, "movie/movie-list.json")

        movieActivityTest.launchActivity(Intent())

        onView(withId(R.id.imageButtonTryAgain)).perform(click())

        onView(withId(R.id.recyclerViewMovie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
    }

    @Test
    fun shouldOpenMovieDetailActivity_WhenAMovieWasSelected() {

        RESTMockServer.whenGET(RequestMatchers.pathContains("movie/upcoming")).thenReturnFile(200, "movie/movie-list.json")

        movieActivityTest.launchActivity(Intent().putExtra(MovieDetailActivity.MOVIE_SELECTED,
                Movie(200, "", 1, true,
                        10.0, "Scott Pilgrim vs. The World", 10.0, "", "en",
                        "Scott Pilgrim vs. The World", listOf(1, 2, 3), "", false, "")))

        val activityResult = Instrumentation.ActivityResult(Activity.RESULT_OK, Intent())

        Intents.intending(IntentMatchers.hasComponent(MovieDetailActivity::class.java.name)).respondWith(activityResult)

        onView(withId(R.id.recyclerViewMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        Intents.intended(allOf(IntentMatchers.hasComponent(MovieDetailActivity::class.java.name),
                IntentMatchers.hasExtraWithKey(MovieDetailActivity.MOVIE_SELECTED)))
    }

}