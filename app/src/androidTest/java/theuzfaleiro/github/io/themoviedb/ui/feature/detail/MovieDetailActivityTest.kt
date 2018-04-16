package theuzfaleiro.github.io.themoviedb.ui.feature.detail

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers
import io.appflate.restmock.utils.RequestMatchers.pathContains
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import theuzfaleiro.github.io.themoviedb.R
import theuzfaleiro.github.io.themoviedb.data.model.movie.Movie

class MovieDetailActivityTest {

    @get:Rule
    val pullRequestActivityTestRule = ActivityTestRule(MovieDetailActivity::class.java, true, false)

    @Before
    fun setUp() {
        RESTMockServer.reset()
    }

    @Test
    fun shouldShowMovieDetail_WhenFetchedMovieDetailDataFromAPI() {
        RESTMockServer.whenGET(pathContains("movie/")).thenReturnFile(200, "detail/movie-detail.json")

        pullRequestActivityTestRule.launchActivity(Intent().putExtra(MovieDetailActivity.MOVIE_SELECTED, getMockedMovie()))

        onView(withId(R.id.textViewMovieName)).check(matches(withText("Black Panther")))


    }

    @Test
    fun shouldOpenMovieDetailActivity_WhenAMovieWasSelected() {

        RESTMockServer.whenGET(RequestMatchers.pathContains("movie/")).thenReturnFile(200, "detail/movie-detail.json")

        pullRequestActivityTestRule.launchActivity(Intent().putExtra(MovieDetailActivity.MOVIE_SELECTED, getMockedMovie()))

        onView(withId(R.id.imageViewMoviePoster)).check(matches(ViewMatchers.withContentDescription(Matchers.containsString("Black Panther"))))


    }

    private fun getMockedMovie() = Movie(1, "", 284054, true, 2.4, "Black Panther", 1.0,
            "", "Black Panther", "Black Panther", listOf(), "", false, "2018")
}