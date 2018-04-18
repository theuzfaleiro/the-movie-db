package theuzfaleiro.github.io.themoviedb.util.rx

import io.reactivex.Scheduler

interface RxSchedulers {

    fun io(): Scheduler

    fun ui(): Scheduler
}