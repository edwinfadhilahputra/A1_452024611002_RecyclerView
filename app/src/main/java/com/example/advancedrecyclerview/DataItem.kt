package com.example.advancedrecyclerview

sealed class DataItem {
    abstract val id: Long

    // Tipe 1: Header untuk memisahkan kelompok data
    data class Header(val title: String) : DataItem() {
        override val id = Long.MIN_VALUE
    }

    // Tipe 2: Item Angka yang akan tampil di dalam Grid
    data class NumberItem(val numberData: Int, override val id: Long) : DataItem()
}