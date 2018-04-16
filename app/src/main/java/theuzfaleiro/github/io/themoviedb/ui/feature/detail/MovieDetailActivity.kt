package theuzfaleiro.github.io.themoviedb.ui.feature.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.partial_layout_error.*
import theuzfaleiro.github.io.themoviedb.R
import theuzfaleiro.github.io.themoviedb.data.model.movie.Movie
import theuzfaleiro.github.io.themoviedb.util.extension.toBackdropUrl
import theuzfaleiro.github.io.themoviedb.util.extension.toPosterUrl
import javax.inject.Inject


class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    private var movieId: Int = 0

    @Inject
    lateinit var movieViewModelFactory: MovieDetailViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setSupportActionBar(findViewById(R.id.toolbarMovieDetail))

        movieId = intent.extras.getParcelable<Movie>(MOVIE_SELECTED).id

        movieDetailViewModel = ViewModelProviders.of(this, movieViewModelFactory).get(MovieDetailViewModel::class.java)

        getMovieDetailsFromSelectedMovie(movieId)

        tryToReloadMovieList()
    }

    private fun getMovieDetailsFromSelectedMovie(movieId: Int) {
        with(movieDetailViewModel) {
            searchedMovie.observe(this@MovieDetailActivity, Observer { movieDetail ->
                with(movieDetail) {
                    textViewMovieName.text = this!!.title
                    textViewOriginalTitle.text = this.originalTitle
                    textViewOriginalLanguage.text = this.originalLanguage
                    textViewReleaseDate.text = this.releaseDate
                    textViewRuntime.text = this.runtime
                    ratingBarVoteAverage.rating = this.voteAverage

                    textViewTagLine.text = tagline
                    textViewOverview.text = overview

                    imageViewMoviePoster.contentDescription = title

                    Glide.with(this@MovieDetailActivity)
                            .load(posterPath?.toPosterUrl() ?: "")
                            .apply(RequestOptions().placeholder(R.drawable.ic_default_movie_cover)
                                    .error(R.drawable.ic_error_movie_cover)
                            )
                            .into(imageViewMoviePoster)

                    Glide.with(this@MovieDetailActivity)
                            .load(backdropPath?.toBackdropUrl())
                            .apply(RequestOptions().placeholder(R.drawable.ic_default_movie_cover)
                                    .error(R.drawable.ic_error_movie_cover)
                            )
                            .into(imageViewBackdrop)
                }

            })


            loading.observe(this@MovieDetailActivity, Observer { isLoading ->
                viewFlipperMovieDetail.displayedChild = loading.value!!
            })

        }

        movieDetailViewModel.getMovieDetails(movieId)
    }


    private fun tryToReloadMovieList() {
        imageButtonTryAgain.setOnClickListener {
            movieDetailViewModel.getMovieDetails(movieId)
        }
    }

    companion object {
        const val MOVIE_SELECTED: String = "MOVIE_SELECTED"
    }
}




