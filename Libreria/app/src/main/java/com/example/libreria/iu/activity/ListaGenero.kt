package com.example.libreria.iu.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libreria.databinding.ActivityListaGeneroBinding
import com.example.libreria.iu.adapters.GeneroAdapter
import com.example.libreria.models.Genero
import com.example.libreria.repositories.GeneroRepository

class listaGenero : AppCompatActivity(), GeneroAdapter.OnGeneroClickListener{
  private lateinit var binding: ActivityListaGeneroBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    binding = ActivityListaGeneroBinding.inflate(layoutInflater)
    setContentView(binding.root)

    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }
    binding.ListGenero.layoutManager = LinearLayoutManager(this)


    ListaGenero()
    eventListener()
  }

  private fun eventListener() {
    binding.AgregarGenero.setOnClickListener {
        val intent = Intent(this, ingresarGenero::class.java)
        startActivity(intent)
    }
  }


  private fun ListaGenero() {
    GeneroRepository.getGeneros(
      success = { generos ->
        generos?.let {
          binding.ListGenero.adapter = GeneroAdapter(it, this)
        }
      },
      failure = {
        it.printStackTrace()
      }
    )
  }

  override fun onGeneroClick(genero: Genero) {
    val intent = Intent(this, LibrosPorGeneroActivity::class.java).apply {
      putExtra("generoId", genero.id)
    }
    startActivity(intent)
  }

  override fun onDeleteGeneroClick(genero: Genero) {
    GeneroRepository.deleteGenero(
      id= genero.id,
      success = {
        ListaGenero()
      },
      failure = {
        it.printStackTrace()
      }
    )
  }

  override fun onEditGeneroClick(genero: Genero) {

    val intent = Intent(this, editarGenero::class.java)
    intent.putExtra("generoId", genero.id)
    intent.putExtra("generoNombre", genero.nombre)
    startActivity(intent)
  }
  //    val intent = Intent(this, editarGenero::class.java).apply {
//      intent.putExtra("generoId", genero.id)
//      intent.putExtra("generoNombre", genero.nombre)
//    }
//    startActivity(intent)





}