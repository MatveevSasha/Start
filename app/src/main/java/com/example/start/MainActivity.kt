package com.example.start

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.start.data.Film
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val filmsDataBase = listOf(
        Film(
            title = "Дюна",
            poster = R.drawable.poster_duna,
            description = "Эпическая история о борьбе за контроль над планетой Арракис"
        ),
        Film(
            title = "Джентельмены",
            poster = R.drawable.poster_djentelmenu,
            description = "Описание"
        ),
        Film(
            title = "Форсаж",
            poster = R.drawable.poster_forsazh,
            description = "Описание"
        ),
        Film(
            title = "Один дома",
            poster = R.drawable.poster_odindoma,
            description = "Описание"
        ),
        Film(
            title = "Друзья",
            poster = R.drawable.poster_druzya,
            description = "Описание"
        ),
        Film(
            title = "Леон",
            poster = R.drawable.poster_leon,
            description = "Описание"
        ),
        Film(
            title = "Волк с Уолл-стрит",
            poster = R.drawable.poster_volksuoltstrit,
            description = "Описание"
        )
    )

    private lateinit var toolbar: MaterialToolbar
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var recyclerView: RecyclerView
    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация RecyclerView
        recyclerView = findViewById(R.id.main_recycler)
        filmsAdapter = FilmListRecyclerAdapter(
            object : FilmListRecyclerAdapter.OnItemClickListener {
                override fun click(film: Film) {
                    // Обработчик клика
                }
            }
        )

        recyclerView.apply {
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)

            // Добавляем отступы
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }

        filmsAdapter.addItems(filmsDataBase)

        toolbar = findViewById(R.id.topAppBar)
        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.app_name)

        bottomNavigation = findViewById(R.id.bottom_nav)
        bottomNavigation.menu.clear()
        bottomNavigation.inflateMenu(R.menu.menu_navigation)

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.favorites -> {
                    showToast("Избранное")
                    true
                }
                R.id.watch_later -> {
                    showToast("Посмотреть позже")
                    true
                }
                R.id.collections -> {
                    showToast("Подборки")
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                showToast("Настройки")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

