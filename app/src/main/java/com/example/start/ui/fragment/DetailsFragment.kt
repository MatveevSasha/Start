package com.example.start.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.start.R
import com.example.start.data.Film
import com.example.start.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private var film: Film? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получаем данные из аргументов фрагмента
        film = arguments?.getParcelable("film")
        if (film == null) {
            requireActivity().finish()
            return
        }

        // Используем безопасный вызов с let
        film?.let { currentFilm ->
            // Инициализируем элементы через binding
            val detailsToolbar = binding.detailsToolbar
            val detailsPoster = binding.detailsPoster
            val detailsDescription = binding.detailsDescription

            // Заполняем элементы интерфейса
            detailsToolbar.title = currentFilm.title

            // Проверяем наличие постера
            if (currentFilm.poster != 0) {
                detailsPoster.setImageResource(currentFilm.poster)
            } else {
                // Устанавливаем постер по умолчанию
                detailsPoster.setImageResource(R.drawable.poster_duna)
            }

            detailsDescription.text = currentFilm.description
        }

        // Настройка toolbar
        (requireActivity() as AppCompatActivity).apply {
            setSupportActionBar(binding.detailsToolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    companion object {
        fun newInstance(film: Film): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putParcelable("film", film)
            fragment.arguments = args
            return fragment
        }
    }
}
