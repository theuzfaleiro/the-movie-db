package theuzfaleiro.github.io.themoviedb.ui.feature.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import dagger.android.AndroidInjection
import theuzfaleiro.github.io.themoviedb.R
import theuzfaleiro.github.io.themoviedb.data.model.movie.Movie
import javax.inject.Inject


class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    @Inject
    lateinit var movieViewModelFactory: MovieDetailViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movieDetailViewModel = ViewModelProviders.of(this, movieViewModelFactory).get(MovieDetailViewModel::class.java)

        getMovieDetailsFromSelectedMovie(intent.extras.getParcelable<Movie>(MOVIE_SELECTED).id)

    }

    private fun getMovieDetailsFromSelectedMovie(movieId: Int) {
        with(movieDetailViewModel) {
            searchedMovie.observe(this@MovieDetailActivity, Observer {
                Toast.makeText(this@MovieDetailActivity, it!!.originalTitle, Toast.LENGTH_LONG).show()
            })
        }

        movieDetailViewModel.getMovieDetails(movieId)
    }

    companion object {
        const val MOVIE_SELECTED: String = "MOVIE_SELECTED"
    }
}
