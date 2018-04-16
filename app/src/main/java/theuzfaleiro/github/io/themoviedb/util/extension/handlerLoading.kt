package theuzfaleiro.github.io.themoviedb.util.extension

import android.arch.lifecycle.MutableLiveData
import io.reactivex.Single

private const val INFORMATION = 0
private const val LOADING = 1
private const val ERROR = 2

fun <T> Single<T>.handlerLoading(loading: MutableLiveData<Int>): Single<T> {
    return this
            .doOnSubscribe { loading.postValue(LOADING) }
            .doOnError { loading.postValue(ERROR) }
            .doOnSuccess { loading.postValue(INFORMATION) }
}
