package com.example.start.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val id: Long,
    val title: String,
    val poster: Int,
    val description: String,
    var isInFavorites: Boolean = false
) : Parcelable {

    // Основной конструктор с валидной инициализацией
    constructor(
        title: String,
        poster: Int,
        description: String,
        id: Long = System.currentTimeMillis() // Генерируем уникальный ID
    ) : this(id, title, poster, description)
}
