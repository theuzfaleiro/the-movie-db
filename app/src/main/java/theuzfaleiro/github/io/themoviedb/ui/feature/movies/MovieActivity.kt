package theuzfaleiro.github.io.themoviedb.ui.feature.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.widget.SearchView
import dagger.android.AndroidInjection
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_movie.*
import theuzfaleiro.github.io.themoviedb.R
import theuzfaleiro.github.io.themoviedb.ui.feature.common.adapter.InfiniteScrollListener
import theuzfaleiro.github.io.themoviedb.ui.feature.detail.MovieDetailActivity
import theuzfaleiro.github.io.themoviedb.ui.feature.movies.adapter.GenericAdapter
import javax.inject.Inject


class MovieActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel

    private lateinit var genericAdapter: GenericAdapter

    private var moviePage: Int = 1

    @Inject
    lateinit var movieViewModelFactory: MovieViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        setSupportActionBar(findViewById(R.id.toolbarMovie))

        movieViewModel = ViewModelProviders.of(this, movieViewModelFactory).get(MovieViewModel::class.java)

        initMovieAdapter()

        initMovieRecyclerView()

        getUpcomingMovieList()
    }

    private fun initMovieAdapter() {
        genericAdapter = GenericAdapter { movieSelected ->
            startActivity(Intent(this@MovieActivity,
                    MovieDetailActivity::class.java).putExtra(MovieDetailActivity.MOVIE_SELECTED, movieSelected))
        }
    }

    private fun initMovieRecyclerView() {
        with(recyclerViewMovie) {

            setHasFixedSize(true)

            val a = GridLayoutManager(this@MovieActivity, 3)

            layoutManager = a

            adapter = genericAdapter

            addOnScrollListener(InfiniteScrollListener({ movieViewModel.getUpcomingMovies(moviePage.inc()) }, layoutManager = a))

        }
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

    private fun getUpcomingMovieList() {

        with(movieViewModel) {

            upcomingMovieList.observe(this@MovieActivity, Observer {
                it?.let {
                    genericAdapter.addNews(it)
                }
            })

            loading.observe(this@MovieActivity, Observer { isLoading ->
                viewFlipperMovie.displayedChild = if (isLoading == true) SHOW_LOADER else SHOW_CONTENT
            })
        }


        movieViewModel.getUpcomingMovies(1)

    }

    companion object {
        private const val SHOW_LOADER = 1
        private const val SHOW_CONTENT = 0
    }
}
