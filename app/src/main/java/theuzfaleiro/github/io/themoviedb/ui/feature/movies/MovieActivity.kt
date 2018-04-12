package theuzfaleiro.github.io.themoviedb.ui.feature.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import dagger.android.AndroidInjection
import theuzfaleiro.github.io.themoviedb.R
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel

    @Inject
    lateinit var movieViewModelFactory: MovieViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        movieViewModel = ViewModelProviders.of(this, movieViewModelFactory).get(MovieViewModel::class.java)

        movie()
    }


    private fun movie() {
        movieViewModel.upcomingMovieList.observe(this@MovieActivity, Observer {
            it?.let {
                Toast.makeText(this, it.first().title, Toast.LENGTH_LONG).show()
            }
        })

        movieViewModel.getUpcomingMovies(1)
    }
}
