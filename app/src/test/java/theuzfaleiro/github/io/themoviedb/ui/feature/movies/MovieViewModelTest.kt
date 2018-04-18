package theuzfaleiro.github.io.themoviedb.ui.feature.movies

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.whenever
import io.github.theuzfaleiro.trendingongithub.utils.RxTestSchedulers
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import theuzfaleiro.github.io.themoviedb.data.network.repository.movie.MovieRepository
import theuzfaleiro.github.io.themoviedb.data.network.response.movie.Movie
import theuzfaleiro.github.io.themoviedb.data.network.response.movie.UpcomingMovies

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private val testSchedulers = TestScheduler()

    private val schedulerProvider = RxTestSchedulers(testSchedulers)

    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository


    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(movieRepository, schedulerProvider)
    }

    @Test
    fun shouldPostMovieList_WhenSuccessful() {
        whenever(movieRepository.getMoviesFromApi(1)).thenReturn(getMockedUpcomingMovies())

        movieViewModel.getUpcomingMovies(1)

        testSchedulers.triggerActions()

        assertThat(movieViewModel.upcomingMovieList.value!!.size, `is`(1))

        assertEquals(movieViewModel.upcomingMovieList.value!!.first().originalTitle, "Scott Pilgrim vs. The World")

    }

    @Test
    fun shouldShownErrorMessage_WhenMovieDataIsEmpty() {
        whenever(movieRepository.getMoviesFromApi(1)).thenReturn(getMockedError())

        movieViewModel.getUpcomingMovies(1)

        testSchedulers.triggerActions()

        assertNull(movieViewModel.upcomingMovieList.value)
    }


    @Test
    fun shouldShownMovieSearched_WhenSuccessful() {
        whenever(movieRepository.searchMovieAtApi("Scott")).thenReturn(getMockedUpcomingMovies())

        movieViewModel.searchFromMovie("Scott")

        testSchedulers.triggerActions()

        assertThat(movieViewModel.searchedMovieList.value!!.size, `is`(1))

        assertEquals(movieViewModel.searchedMovieList.value!!.first().originalTitle, "Scott Pilgrim vs. The World")
    }


    @Test
    fun shouldShownErrorMessage_WhenMovieSearchDoesNotExist() {
        whenever(movieRepository.searchMovieAtApi("Scott")).thenReturn(getMockedError())

        movieViewModel.searchFromMovie("Scott")

        testSchedulers.triggerActions()

        assertNull(movieViewModel.upcomingMovieList.value)
    }

    private fun getMockedUpcomingMovies(): Single<UpcomingMovies> =
            Single.just(UpcomingMovies(listOf(Movie("", 284054, 1.1, "Scott Pilgrim vs. The World",
                    "r", "en",
                    "Scott Pilgrim vs. The World", listOf(), "", "2018"))))

    private fun getMockedError(): Single<UpcomingMovies> = Single.error(Throwable())
}