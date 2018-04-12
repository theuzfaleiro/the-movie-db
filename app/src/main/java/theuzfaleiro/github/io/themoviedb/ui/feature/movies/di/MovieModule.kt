package theuzfaleiro.github.io.themoviedb.ui.feature.movies.di

import dagger.Module
import dagger.Provides
import theuzfaleiro.github.io.themoviedb.data.network.TheMovieDbEndpoint
import theuzfaleiro.github.io.themoviedb.data.network.repository.movie.MovieRepository
import theuzfaleiro.github.io.themoviedb.ui.feature.movies.MovieViewModelFactory
import theuzfaleiro.github.io.themoviedb.util.Rx.RxSchedulers

@Module
class MovieModule {

    @Provides
    fun providesMovieRepository(theMovieDbEndpoint: TheMovieDbEndpoint) =
            MovieRepository(theMovieDbEndpoint)

    @Provides
    fun provideProductDetailFactory(movieRepository: MovieRepository, rxSchedulers: RxSchedulers) =
            MovieViewModelFactory(movieRepository, rxSchedulers)
}