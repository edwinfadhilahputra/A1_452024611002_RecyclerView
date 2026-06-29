package com.example.advancedrecyclerview

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("customNumberStyle")
fun TextView.setCustomNumberStyle(number: Int) {
    text = number.toString()
    // Logika presentasi: Genap = Hijau, Ganjil = Merah
    if (number % 2 == 0) {
        setTextColor(Color.parseColor("#4CAF50")) // Hijau
    } else {
        setTextColor(Color.parseColor("#F44336")) // Merah
    }
}