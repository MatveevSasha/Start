package com.example.start.ui.fragment

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.start.R
import com.example.start.data.Film
import com.example.start.databinding.FragmentDetailsBinding
import com.google.android.material.snackbar.Snackbar
import com.example.start.ui.activity.MainActivity

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

        film = arguments?.getParcelable("film")
        if (film == null) {
            Snackbar.make(requireView(), "Фильм не найден", Snackbar.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.popBackStack()
            return
        }

        with(binding) {
            try {
                detailsPoster.setImageResource(film!!.poster)
            } catch (e: Resources.NotFoundException) {
                Snackbar.make(requireView(), "Изображение не найдено", Snackbar.LENGTH_SHORT).show()
            }

            detailsTitle.text = film?.title ?: "Название не найдено"
            detailsDescription.text = film?.description ?: "Описание отсутствует"

            // Обновляем иконку избранного при создании
            updateFavoriteIcon()

            btnFavorite.setOnClickListener {
                film!!.isInFavorites = !film!!.isInFavorites
                updateFavoriteIcon()
                (requireActivity() as MainActivity).addToFavorites(film!!)
            }

            detailsFab.setOnClickListener {
                shareFilm()
            }
        }
    }

    private fun updateFavoriteIcon() {
        binding.btnFavorite.setImageResource(
            if (film?.isInFavorites == true) {
                R.drawable.round_favorite
            } else {
                R.drawable.ic_favorite_border
            }
        )
    }

    private fun shareFilm() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Рекомендую посмотреть фильм ${film?.title}!\n" +
                    "Описание: ${film?.description}"
        )
        startActivity(Intent.createChooser(shareIntent, "Поделиться фильмом"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        film = null
    }
}

