package theuzfaleiro.github.io.themoviedb.ui.feature.movies

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieActivityTest {

    @get:Rule
    val pullRequestActivityTestRule = IntentsTestRule(MovieActivity::class.java, true, false)

    @Before
    fun setUp() {
        RESTMockServer.reset()
    }

    @Test
    fun shouldShowMovieList_WhenFetchedMovieDataFromAPI() {
        RESTMockServer.whenGET(RequestMatchers.pathContains("movie/")).thenReturnFile(200, "movie/movie-list.json")

        pullRequestActivityTestRule.launchActivity(Intent())

        Espresso.onView(ViewMatchers.withText("Vingadores: Guerra Infinita")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}