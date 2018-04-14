package theuzfaleiro.github.io.themoviedb.util.extension

import android.arch.lifecycle.MutableLiveData
import io.reactivex.Single

fun <T> Single<T>.handlerLoading(loading: MutableLiveData<Boolean>): Single<T> {
    return this
            .doOnSubscribe { loading.postValue(true) }
            .doOnError { loading.postValue(false) }
            .doOnSuccess { loading.postValue(false) }
}