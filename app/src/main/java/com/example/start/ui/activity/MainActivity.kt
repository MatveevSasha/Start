package com.example.start.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.example.start.R
import com.example.start.ui.fragment.HomeFragment
import com.example.start.ui.fragment.DetailsFragment
import com.example.start.ui.fragment.FavoritesFragment
import com.example.start.data.Film
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: MaterialToolbar
    private lateinit var favoritesFragment: FavoritesFragment
    private val favoritesList = mutableListOf<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.topAppBar)
        toolbar.visibility = View.GONE

        // Получаем существующий фрагмент или создаем новый
        favoritesFragment = supportFragmentManager.findFragmentByTag("favorites") as? FavoritesFragment
            ?: FavoritesFragment()

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.favorites -> {
                    toolbar.visibility = View.VISIBLE
                    toolbar.title = getString(R.string.app_name)
                    true
                }
                else -> {
                    toolbar.visibility = View.GONE
                    toolbar.title = ""
                    true
                }
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_placeholder, HomeFragment())
                .addToBackStack("home")
                .commit()
        }
    }

    fun addToFavorites(film: Film) {
        film.isInFavorites = !film.isInFavorites // Сначала меняем статус
        if (film.isInFavorites) {
            favoritesList.add(film)
        } else {
            favoritesList.remove(film)
        }
        favoritesFragment.updateFavorites(favoritesList)
    }

    fun launchDetailsFragment(film: Film) {
        val bundle = Bundle()
        bundle.putParcelable("film", film)

        val fragment = DetailsFragment()
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack("details")
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Сохраняем список избранного
        outState.putParcelableArrayList("favorites", favoritesList as ArrayList<Parcelable>)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Восстанавливаем список избранного
        favoritesList.clear()
        favoritesList.addAll(savedInstanceState.getParcelableArrayList("favorites") ?: mutableListOf())
    }
}
