package com.example.start

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.AnimationUtils // Исправлено
import android.widget.GridLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var gridLayout: GridLayout // Теперь правильно

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

// Находим GridLayout
        gridLayout = findViewById<GridLayout>(R.id.gridLayout) // Явное приведение типа

// Добавляем анимацию
        try {
            val layoutAnimation = AnimationUtils.loadLayoutAnimation(
                this,
                R.anim.card_layout_animation
            )
            gridLayout.layoutAnimation = layoutAnimation
        } catch (e: Exception) {
            Toast.makeText(this, "Ошибка загрузки анимации", Toast.LENGTH_SHORT).show()
        }

// Настройка Toolbar
        toolbar = findViewById(R.id.topAppBar)
        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.app_name)

// Настройка BottomNavigation
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

