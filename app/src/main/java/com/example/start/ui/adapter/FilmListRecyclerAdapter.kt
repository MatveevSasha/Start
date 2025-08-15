package com.example.start.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.start.R
import com.example.start.data.Film
import com.example.start.ui.adapter.FilmViewHolder

class FilmListRecyclerAdapter(private val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<FilmViewHolder>() {

    // Список элементов
    private val items = mutableListOf<Film>()

    // Возвращаем количество элементов
    override fun getItemCount(): Int = items.size

    // Создаем ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.film_item, parent, false)
        )
    }

    // Привязываем данные к ViewHolder и добавляем обработчик клика
    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(items[position])

        // Добавляем обработчик клика на элемент
        holder.itemView.setOnClickListener {
            clickListener.click(items[position])
        }
    }

    // Метод для добавления данных
    fun addItems(list: List<Film>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    // Интерфейс для обработки кликов
    interface OnItemClickListener {
        fun click(film: Film)
    }
}