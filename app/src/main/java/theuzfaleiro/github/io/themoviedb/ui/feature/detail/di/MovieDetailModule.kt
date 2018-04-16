package theuzfaleiro.github.io.themoviedb.ui.feature.detail.di

import dagger.Module
import dagger.Provides
import theuzfaleiro.github.io.themoviedb.data.network.TheMovieDbEndpoint
import theuzfaleiro.github.io.themoviedb.data.network.repository.detail.MovieDetailRepository
import theuzfaleiro.github.io.themoviedb.ui.feature.detail.MovieDetailViewModelFactory
import theuzfaleiro.github.io.themoviedb.util.rx.RxSchedulers

@Module
class MovieDetailModule {

    @Provides
    fun providesMovieRepository(theMovieDbEndpoint: TheMovieDbEndpoint) =
            MovieDetailRepository(theMovieDbEndpoint)

    @Provides
    fun provideProductDetailFactory(movieDetailRepository: MovieDetailRepository, rxSchedulers: RxSchedulers) =
            MovieDetailViewModelFactory(movieDetailRepository, rxSchedulers)
}