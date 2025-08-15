package com.example.start.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.start.R
import com.example.start.data.Film
import com.example.start.databinding.FilmItemBinding

class FilmAdapter : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    private var filmList = emptyList<Film>()

    inner class FilmViewHolder(private val binding: FilmItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {
            binding.apply {
                poster.setImageResource(film.poster)
                title.text = film.title
                description.text = film.description
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
}

