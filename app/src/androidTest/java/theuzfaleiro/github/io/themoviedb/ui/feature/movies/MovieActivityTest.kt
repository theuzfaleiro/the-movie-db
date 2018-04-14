package theuzfaleiro.github.io.themoviedb.ui.feature.movies

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import android.support.test.espresso.matcher.ViewMatchers.withId
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers
import org.hamcrest.Matchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import theuzfaleiro.github.io.themoviedb.R

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

        onView(withId(R.id.imageViewMoviePoster)).check(matches(withContentDescription(containsString("Vingadores: Guerra Infinita"))));
    }
}