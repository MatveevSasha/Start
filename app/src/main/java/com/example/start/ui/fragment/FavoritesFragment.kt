package com.example.start.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.start.R
import com.example.start.data.Film
import com.example.start.databinding.FragmentFavoritesBinding
import com.example.start.ui.adapter.FilmAdapter

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var favoritesList: MutableList<Film>
    private lateinit var adapter: FilmAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализируем пустой список
        favoritesList = mutableListOf()

        // Создаем адаптер
        adapter = FilmAdapter()

        // Устанавливаем обработчик кликов
        adapter.setOnItemClickListener { film ->
            launchDetailsFragment(film)
        }

        // Настраиваем RecyclerView
        binding.recyclerFavorites.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        // Обновляем состояние при изменении списка
        updateEmptyState()
    }

    // Метод для обновления списка избранного
    fun updateFavorites(favorites: List<Film>) {
        favoritesList.clear()
        favoritesList.addAll(favorites)
        adapter.setFilms(favoritesList)
        updateEmptyState()
    }

    private fun updateEmptyState() {
        binding.tvEmpty.visibility = if (favoritesList.isEmpty()) View.VISIBLE else View.GONE
        binding.recyclerFavorites.visibility = if (favoritesList.isEmpty()) View.GONE else View.VISIBLE
    }

    private fun launchDetailsFragment(film: Film) {
        val fragment = DetailsFragment()
        val bundle = Bundle().apply {
            putParcelable("film", film)
        }
        fragment.arguments = bundle
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack("details")
            .commit()
    }

    // Методы для работы с избранным
    private fun addToFavorites(film: Film) {
        if (!favoritesList.contains(film)) {
            film.isInFavorites = true
            favoritesList.add(film)
            adapter.setFilms(favoritesList)
            updateEmptyState()
        }
    }

    private fun removeFromFavorites(film: Film) {
        if (favoritesList.contains(film)) {
            film.isInFavorites = false
            favoritesList.remove(film)
            adapter.setFilms(favoritesList)
            updateEmptyState()
        }
    }
}
