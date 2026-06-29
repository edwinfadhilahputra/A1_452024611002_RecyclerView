package com.example.advancedrecyclerview

import android.os.Bundle
import androidx.activity.ComponentActivity // GUNAKAN IMPORT INI
import androidx.recyclerview.widget.GridLayoutManager
import com.example.advancedrecyclerview.databinding.ActivityMainBinding

// Ubah dari AppCompatActivity() menjadi ComponentActivity()
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Adapter
        val adapter = AdvancedAdapter()
        val gridLayoutManager = GridLayoutManager(this, 3)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    0 -> 3 // Header penuhi 3 kolom
                    1 -> 1 // Item angka ambil 1 kolom
                    else -> 1
                }
            }
        }

        binding.recyclerView.layoutManager = gridLayoutManager
        binding.recyclerView.adapter = adapter

        // Dummy Data
        val dummyData = listOf(
            DataItem.Header("Kelompok Angka Genap & Ganjil"),
            DataItem.NumberItem(12, 1L),
            DataItem.NumberItem(25, 2L),
            DataItem.NumberItem(30, 3L),
            DataItem.Header("Kelompok Angka Besar"),
            DataItem.NumberItem(41, 4L),
            DataItem.NumberItem(56, 5L),
            DataItem.NumberItem(63, 6L),
            DataItem.NumberItem(78, 7L),
            DataItem.NumberItem(85, 8L)
        )

        adapter.submitList(dummyData)
    }
}