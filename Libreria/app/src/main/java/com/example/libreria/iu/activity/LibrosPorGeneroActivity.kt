package com.example.libreria.iu.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libreria.iu.adapters.LibroAdapter
import com.example.libreria.databinding.ActivityLibrosPorGeneroBinding
import com.example.libreria.models.Libro
import com.example.libreria.repositories.LibroRepository

class LibrosPorGeneroActivity : AppCompatActivity(), LibroAdapter.OnLibroClickListener {

    private lateinit var binding: ActivityLibrosPorGeneroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLibrosPorGeneroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewLibrosPorGenero.layoutManager = LinearLayoutManager(this)

        val generoId = intent.getIntExtra("generoId", -1)
        fetchLibrosPorGenero(generoId)
    }

    private fun fetchLibrosPorGenero(generoId: Int) {
        LibroRepository.getLibros(
            success = { libros ->
                libros?.let {
                    val librosFiltrados = it.filter { libro -> libro.generos.any { genero -> genero.id == generoId } }
                    binding.recyclerViewLibrosPorGenero.adapter = LibroAdapter(librosFiltrados, this)
                }
            },
            failure = {
                it.printStackTrace()
            })
    }

    override fun onLibroClick(libro: Libro) {
        val intent = Intent(this, Detalle::class.java).apply {
            putExtra("nombre", libro.nombre)
            putExtra("autor", libro.autor)
            putExtra("editorial", libro.editorial)
            putExtra("sinopsis", libro.sinopsis)
            putExtra("isbn", libro.isbn)
            putExtra("calificacion", libro.calificacion)
            putExtra("imagen", libro.imagen)
        }
        startActivity(intent)
    }
}
