package com.example.libreria.iu.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.libreria.R
import com.example.libreria.databinding.ActivityEditarGeneroBinding
import com.example.libreria.models.Genero
import com.example.libreria.repositories.GeneroRepository
import kotlin.math.log

class editarGenero : AppCompatActivity(){
  private lateinit var binding: ActivityEditarGeneroBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    binding = ActivityEditarGeneroBinding.inflate(layoutInflater)
    setContentView(binding.root)

    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }
    val generoId = intent.getIntExtra("generoId",0)
    Log.d("generoId", generoId.toString())
    val generoNombre = intent.getStringExtra("generoNombre")
    Log.d("generoNombre", generoNombre.toString())
    binding.txtEditarGenero.setText(generoNombre)

    binding.btnEditarGenero.setOnClickListener {
      val nombreGenero = binding.txtEditarGenero.text.toString()
      val genero = Genero(id = generoId, nombre = nombreGenero)
      GeneroRepository.updateGenero(
        id = genero.id,
        genero = genero,
        success = {
          finish()
        },
        failure = {
          it.printStackTrace()
        }
      )
    }

  }


}