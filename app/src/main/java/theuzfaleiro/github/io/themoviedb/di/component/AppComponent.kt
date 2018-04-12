package theuzfaleiro.github.io.themoviedb.di.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import theuzfaleiro.github.io.themoviedb.TheMovieDbApplication
import theuzfaleiro.github.io.themoviedb.data.network.RetrofitConfigModule
import theuzfaleiro.github.io.themoviedb.di.builder.ActivityBuilder
import theuzfaleiro.github.io.themoviedb.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (RetrofitConfigModule::class), (ActivityBuilder::class)])
internal interface AppComponent : AndroidInjector<TheMovieDbApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TheMovieDbApplication>()
}