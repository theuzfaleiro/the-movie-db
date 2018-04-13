package theuzfaleiro.github.io.themoviedb.ui.feature.movies

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.reactivex.Observable
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import theuzfaleiro.github.io.themoviedb.data.model.movie.Movie
import theuzfaleiro.github.io.themoviedb.data.network.repository.movie.MovieRepository
import theuzfaleiro.github.io.themoviedb.util.Rx.RxSchedulers
import javax.inject.Inject
import javax.inject.Singleton

class MovieViewModel(private val movieRepository: MovieRepository, private val rxSchedulers: RxSchedulers) : ViewModel() {

    val upcomingMovieList = MutableLiveData<List<Movie>>()

    fun getUpcomingMovies(page: Int = 1) {
        movieRepository.getMoviesFromApi(page)
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.ui())
                .flatMap {
                    Observable
                            .fromIterable(it.results)
                            .map {
                                Movie(it)
                            }
                            .toList()
                }
                .subscribeWith(object : SingleObserver<List<Movie>> {
                    override fun onSubscribe(disposable: Disposable) {
                    }

                    override fun onSuccess(movieList: List<Movie>) {
                        upcomingMovieList.postValue(movieList)
                    }

                    override fun onError(error: Throwable) {
                    }
                })
    }
}

@Singleton
class MovieViewModelFactory @Inject constructor(private val movieRepository: MovieRepository, private val rxSchedulers: RxSchedulers) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) = MovieViewModel(movieRepository, rxSchedulers) as T
}