package com.example.libreria.iu.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libreria.R
import com.example.libreria.databinding.ActivityListaLibrosBinding
import com.example.libreria.iu.adapters.LibroAdapter
import com.example.libreria.models.Libro
import com.example.libreria.repositories.LibroRepository

class listaLibros : AppCompatActivity() , LibroAdapter.OnLibroClickListener{
  private lateinit var binding: ActivityListaLibrosBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    binding = ActivityListaLibrosBinding.inflate(layoutInflater)
    setContentView(binding.root)

    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }
    binding.recyclerViewLibros.layoutManager = LinearLayoutManager(this)
    cargarLibro()

  }

  private fun cargarLibro() {
    LibroRepository.getLibros(
      success = { libros ->
        libros?.let {
          val librosOrdenados = it.sortedByDescending { libro -> libro.calificacion }
          binding.recyclerViewLibros.adapter = LibroAdapter(librosOrdenados, this)
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

  override fun onLibroDeleteClick(libro: Libro) {
    LibroRepository.deleteLibro(libro.id,
      success = {
        cargarLibro()
      },
      failure = {
        it.printStackTrace()
      })
  }

  override fun editarLibro(libro: Libro) {

  }

}