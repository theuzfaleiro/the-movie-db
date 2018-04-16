package theuzfaleiro.github.io.themoviedb.ui.feature.movies

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import theuzfaleiro.github.io.themoviedb.data.model.movie.Movie
import theuzfaleiro.github.io.themoviedb.data.network.repository.movie.MovieRepository
import theuzfaleiro.github.io.themoviedb.util.extension.handlerLoading
import theuzfaleiro.github.io.themoviedb.util.rx.RxSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

class MovieViewModel(private val movieRepository: MovieRepository, private val rxSchedulers: RxSchedulers) : ViewModel() {

    val upcomingMovieList = MutableLiveData<List<Movie>>()
    val searchedMovieList = MutableLiveData<List<Movie>>()

    val loading = MutableLiveData<Int>()

    fun getUpcomingMovies(page: Int = 2) {
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
                        loading.postValue(0)
                        upcomingMovieList.postValue(movieList)
                    }

                    override fun onError(error: Throwable) {
                        loading.postValue(2)
                    }
                })
    }

    fun searchFromMovie(movieName: String) {
        movieRepository.searchMovieAtApi(movieName)
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.ui())
                .handlerLoading(loading)
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
                        searchedMovieList.postValue(movieList)
                    }

                    override fun onError(error: Throwable) {
                    }
                })
    }

    fun searchMovieNameWithGivenString(searchObserver: Observable<String>) {
        searchObserver.debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMapSingle {
                    movieRepository.searchMovieAtApi(it)
                }.subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.ui())
                .flatMapSingle {
                    Observable
                            .fromIterable(it.results)
                            .map {
                                Movie(it)
                            }
                            .toList()
                }
                .subscribeWith(object : Observer<List<Movie>> {
                    override fun onComplete() {
                    }

                    override fun onNext(t: List<Movie>) {
                        searchedMovieList.postValue(t)
                    }

                    override fun onSubscribe(disposable: Disposable) {
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