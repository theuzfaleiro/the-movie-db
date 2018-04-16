package theuzfaleiro.github.io.themoviedb.ui.feature.detail

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.whenever
import io.github.theuzfaleiro.trendingongithub.utils.RxTestSchedulers
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import theuzfaleiro.github.io.themoviedb.data.network.repository.detail.MovieDetailRepository
import theuzfaleiro.github.io.themoviedb.data.network.response.detail.MovieDetail

@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {
    private val testSchedulers = TestScheduler()

    private val schedulerProvider = RxTestSchedulers(testSchedulers)

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieDetailRepository: MovieDetailRepository


    @Before
    fun setUp() {
        movieDetailViewModel = MovieDetailViewModel(movieDetailRepository, schedulerProvider)
    }

    @Test
    fun shouldShowMovieDetail_WhenSuccessful() {
        whenever(movieDetailRepository.getMoviesFromApi(1)).thenReturn(getMockedMovieDetail())

        movieDetailViewModel.getMovieDetails(1)

        testSchedulers.triggerActions()

        assertEquals(movieDetailViewModel.searchedMovie.value!!.originalTitle, "Scott Pilgrim vs. The World")

        assertFalse(movieDetailViewModel.searchedMovie.value!!.adult)
    }

    @Test
    fun shouldShownErrorMessage_WhenMovieDataIsEmpty() {
        whenever(movieDetailRepository.getMoviesFromApi(1)).thenReturn(getMockedError())

        movieDetailViewModel.getMovieDetails(1)

        testSchedulers.triggerActions()

        assertNull(movieDetailViewModel.searchedMovie.value)
    }


    private fun getMockedMovieDetail(): Single<MovieDetail> =
            Single.just(MovieDetail(false, "", 284054, listOf(), 500, "Black Panther", "en",
                    "Scott Pilgrim vs. The World", "", 100.0, "",
                    listOf(), listOf(), "", 1000, 143, listOf(), "open", "Scott vs. The World", "Scott Pilgrim vs. The World", false, 5.0, 10))


    private fun getMockedError(): Single<MovieDetail> = Single.error(Throwable())
}