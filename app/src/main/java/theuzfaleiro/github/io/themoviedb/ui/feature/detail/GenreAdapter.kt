package theuzfaleiro.github.io.themoviedb.ui.feature.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_movie_genre.view.*
import theuzfaleiro.github.io.themoviedb.R
import theuzfaleiro.github.io.themoviedb.data.model.detail.Genres

class GenreAdapter(private val genreList: List<Genres> = listOf()) : RecyclerView.Adapter<GenreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie_genre, parent, false))
    }

    override fun getItemCount(): Int = genreList.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bindItemsToView(genreList[position])
    }
}

class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val genreName = itemView.textViewMovieGenre

    fun bindItemsToView(pullRequest: Genres) {

        genreName.text = pullRequest.name
    }
}