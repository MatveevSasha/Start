package com.example.start.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.start.R
import com.example.start.data.Film
import com.example.start.databinding.FilmItemBinding
import com.google.android.material.snackbar.Snackbar

class FilmAdapter : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    private var filmList = emptyList<Film>()
    private var onItemClickListener: ((Film) -> Unit)? = null

    // Добавляем определение интерфейса
    interface OnItemClickListener {
        fun onItemClick(film: Film)
    }

    inner class FilmViewHolder(private val binding: FilmItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {
            try {
                binding.apply {
                    poster.setImageResource(film.poster)
                    title.text = film.title
                    description.text = film.description

                    favoriteIcon.isVisible = film.isInFavorites
                    favoriteIcon.setImageResource(
                        if (film.isInFavorites) {
                            R.drawable.round_favorite
                        } else {
                            R.drawable.ic_favorite_border
                        }
                    )

                    root.setOnClickListener {
                        onItemClickListener?.invoke(film)
                    }
                }
            } catch (e: Exception) {
                Snackbar.make(
                    binding.root,
                    "Ошибка при отображении фильма: ${e.message}",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = FilmItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(filmList[position])
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    fun setFilms(films: List<Film>) {
        filmList = films
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Film) -> Unit) {
        onItemClickListener = listener
    }
}
