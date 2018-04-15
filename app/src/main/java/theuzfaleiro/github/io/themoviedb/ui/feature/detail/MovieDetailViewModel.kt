package theuzfaleiro.github.io.themoviedb.ui.feature.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import theuzfaleiro.github.io.themoviedb.data.model.detail.MovieDetail
import theuzfaleiro.github.io.themoviedb.data.network.repository.detail.MovieDetailRepository
import theuzfaleiro.github.io.themoviedb.util.Rx.RxSchedulers
import theuzfaleiro.github.io.themoviedb.util.extension.handlerLoading
import javax.inject.Inject
import javax.inject.Singleton

class MovieDetailViewModel(private val movieDetailRepository: MovieDetailRepository, private val rxSchedulers: RxSchedulers) : ViewModel() {

    val searchedMovie = MutableLiveData<MovieDetail>()
    val loading = MutableLiveData<Boolean>()

    fun getMovieDetails(movieId: Int) {
        movieDetailRepository.getMoviesFromApi(movieId)
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.ui())
                .handlerLoading(loading)
                .map {
                    MovieDetail(it)
                }.subscribeWith(object : SingleObserver<MovieDetail> {
                    override fun onSubscribe(disposable: Disposable) {
                    }

                    override fun onSuccess(movieDetail: MovieDetail) {
                        searchedMovie.postValue(movieDetail)
                    }

                    override fun onError(error: Throwable) {
                    }

                })
    }
}

@Singleton
class MovieDetailViewModelFactory @Inject constructor(private val movieDetailRepository: MovieDetailRepository, private val rxSchedulers: RxSchedulers) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) = MovieDetailViewModel(movieDetailRepository, rxSchedulers) as T
}