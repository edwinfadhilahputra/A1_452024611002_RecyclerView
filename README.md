# Advanced RecyclerView Grid Application

Aplikasi Android ini mendemonstrasikan implementasi tingkat lanjut (*advanced*) dari komponen `RecyclerView` menggunakan arsitektur modern di Android Studio. Proyek ini mengintegrasikan struktur data heterogen (*multi-view type*) berupa elemen teks judul (**Header**) dan elemen matriks data angka (**NumberItem**) di dalam satu komponen `GridLayoutManager` yang diatur secara dinamis.

---

## 🚀 Fitur Utama

- **Multi-View Type Layout**: Mampu menampilkan dua tipe tampilan layout yang berbeda secara dinamis di dalam satu daftar adapter tunggal menggunakan implementasi *Sealed Class* di Kotlin.
- **Dinamis Span Size Lookup**: Mengatur pembagian kolom grid secara asimetris. Komponen *Header* dikonfigurasi untuk memenuhi satu baris utuh secara penuh (3 kolom), sedangkan komponen item angka disusun berjejer membentuk kolom matriks yang seimbang (1 kolom).
- **Evaluasi Kondisional & Logika Pewarnaan**: Sistem mengevaluasi tipe data numerik saat proses rendering data terjadi. Jika nilai angka memenuhi kondisi matematis `number % 2 == 0` (Angka Genap), komponen teks akan secara otomatis diwarnai dengan warna **Hijau**. Sebaliknya, jika bernilai ganjil, komponen teks diwarnai **Merah**.
- **ListAdapter & DiffUtil Optimization**: Menggunakan mekanisme komputasi asinkronus dari `DiffUtil` untuk menghitung perubahan data secara spesifik, menghindari re-render total yang tidak efisien (`notifyDataSetChanged`), dan memberikan animasi transisi pembaruan yang mulus.

---

## 🛠️ Struktur Komponen Kode Utama

### 1. Model Data Heterogen (Sealed Class)
Kelas pembungkus data menggunakan implementasi *Sealed Class* untuk menjamin keamanan tipe objek data (*compile-time safety*):
```kotlin
// DataItem.kt
sealed class DataItem {
    abstract val id: Long

    data class Header(val title: String) : DataItem() {
        override val id: Long = title.hashCode().toLong()
    }

    data class NumberItem(val numberData: Int, override val id: Long) : DataItem()
}
