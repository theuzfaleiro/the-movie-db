package theuzfaleiro.github.io.themoviedb.di.builder

import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import theuzfaleiro.github.io.themoviedb.di.scope.PerActivity
import theuzfaleiro.github.io.themoviedb.ui.feature.movies.MovieActivity
import theuzfaleiro.github.io.themoviedb.ui.feature.movies.di.MovieModule

@Module(includes = [(AndroidInjectionModule::class)])
internal abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = [(MovieModule::class)])
    internal abstract fun movieActivityInjector(): MovieActivity
}