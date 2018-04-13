package theuzfaleiro.github.io.themoviedb.ui.feature.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movie.*
import theuzfaleiro.github.io.themoviedb.R
import theuzfaleiro.github.io.themoviedb.data.model.movie.Movie
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
                initPullRequestRecyclerView(it)
            }
        })

        movieViewModel.getUpcomingMovies(1)
    }


    private fun initPullRequestRecyclerView(it: List<Movie>) {
        with(recyclerViewMovie) {
            layoutManager = LinearLayoutManager(this@MovieActivity,
                    LinearLayoutManager.VERTICAL, false)
            adapter = MovieAdapter(it) {
                Toast.makeText(this@MovieActivity, it.originalTitle, Toast.LENGTH_LONG).show()
            }

            setHasFixedSize(true)
        }
    }
}
