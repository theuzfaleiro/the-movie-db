package io.github.theuzfaleiro.trendingongithub.utils

import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler
import theuzfaleiro.github.io.themoviedb.util.rx.RxSchedulers

class RxTestSchedulers(private val testSchedulers: TestScheduler) : RxSchedulers {

    override fun io(): Scheduler = testSchedulers

    override fun ui(): Scheduler = testSchedulers
}