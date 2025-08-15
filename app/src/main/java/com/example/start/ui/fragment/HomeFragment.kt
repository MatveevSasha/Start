package com.example.start.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager // Добавлен импорт
import com.example.start.databinding.FragmentHomeBinding
import com.example.start.ui.adapter.FilmAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: FilmAdapter // Объявлено поле класса

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Используем Data Binding
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализируем адаптер
        adapter = FilmAdapter()

        // Настраиваем RecyclerView
        binding.mainRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext()) // Используем requireContext()
            adapter = this@HomeFragment.adapter
        }
    }
}
