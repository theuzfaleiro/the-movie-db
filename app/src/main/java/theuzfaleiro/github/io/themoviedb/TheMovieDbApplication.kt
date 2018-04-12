package theuzfaleiro.github.io.themoviedb

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import theuzfaleiro.github.io.themoviedb.di.component.DaggerAppComponent
import javax.inject.Inject

open class TheMovieDbApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder().create(this).inject(this)
    }

    override fun activityInjector() = activityInjector

    open fun getBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
}