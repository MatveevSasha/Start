package com.example.start.ui.activity

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.Toolbar
import com.example.start.R
import com.example.start.data.Film

class DetailsActivity : AppCompatActivity() {
    private lateinit var film: Film

    // Объявляем переменные для View
    private lateinit var detailsToolbar: Toolbar
    private lateinit var detailsPoster: AppCompatImageView
    private lateinit var detailsDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)

        // Инициализируем View через findViewById
        detailsToolbar = findViewById(R.id.details_toolbar)
        detailsPoster = findViewById(R.id.details_poster)
        detailsDescription = findViewById(R.id.details_description)

        // Получаем фильм из переданных данных
        film = intent.extras?.getParcelable<Film>("film") ?: run {
            finish()
            return
        }

        // Заполняем элементы интерфейса
        detailsToolbar.title = film.title
        detailsPoster.setImageResource(film.poster)
        detailsDescription.text = film.description
    }
}