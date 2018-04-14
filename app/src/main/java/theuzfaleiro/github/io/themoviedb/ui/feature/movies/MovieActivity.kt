package theuzfaleiro.github.io.themoviedb.ui.feature.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import dagger.android.AndroidInjection
import io.reactivex.subjects.PublishSubject
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

        setSupportActionBar(findViewById(R.id.toolbarMovie))

        movieViewModel = ViewModelProviders.of(this, movieViewModelFactory).get(MovieViewModel::class.java)


        movie()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.movie_menu, menu)

        val searchEditText = menu.findItem(R.id.action_search).actionView as SearchView;

        val publishSubject = PublishSubject.create<String>()

        searchEditText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(movieName: String): Boolean {

                movieViewModel.searchFromMovie(movieName)

                return false
            }

            override fun onQueryTextChange(movieName: String): Boolean {

                publishSubject.onNext(movieName)

                return false
            }
        })

        movieViewModel.searchMovieNameWithGivenString(publishSubject)

        return super.onCreateOptionsMenu(menu)
    }

    private fun movie() {
        movieViewModel.upcomingMovieList.observe(this@MovieActivity, Observer {
            it?.let {
                initMovieRecyclerView(it)
            }
        })

        movieViewModel.loading.observe(this@MovieActivity, Observer { isLoading ->
            viewFlipperMovie.displayedChild = if (isLoading == true) SHOW_LOADER else SHOW_CONTENT
        })

        movieViewModel.getUpcomingMovies(1)
    }


    private fun initMovieRecyclerView(it: List<Movie>) {
        with(recyclerViewMovie) {
            layoutManager = GridLayoutManager(this@MovieActivity,
                    3)
            adapter = MovieAdapter(it) {
                Toast.makeText(this@MovieActivity, it.originalTitle, Toast.LENGTH_LONG).show()
            }

            setHasFixedSize(true)
        }
    }

    companion object {
        private const val SHOW_LOADER = 1
        private const val SHOW_CONTENT = 0
    }
}
