package theuzfaleiro.github.io.themoviedb.util.Rx

import io.reactivex.Scheduler

interface RxSchedulers {

    fun io(): Scheduler

    fun ui(): Scheduler
}