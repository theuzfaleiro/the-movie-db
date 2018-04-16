package theuzfaleiro.github.io.themoviedb.ui.feature.movies.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import theuzfaleiro.github.io.themoviedb.R
import theuzfaleiro.github.io.themoviedb.ui.feature.common.adapter.ViewType
import theuzfaleiro.github.io.themoviedb.ui.feature.common.adapter.ViewTypeAdapter

class LoadingAdapter : ViewTypeAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = LoadingViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {}

    class LoadingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false))
}