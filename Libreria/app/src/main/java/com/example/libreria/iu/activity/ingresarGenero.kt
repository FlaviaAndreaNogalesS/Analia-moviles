package com.example.libreria.iu.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.libreria.R
import com.example.libreria.databinding.ActivityIngresarGeneroBinding
import com.example.libreria.models.Genero
import com.example.libreria.repositories.GeneroRepository

class ingresarGenero : AppCompatActivity() {
  private lateinit var binding: ActivityIngresarGeneroBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    binding = ActivityIngresarGeneroBinding.inflate(layoutInflater)
    setContentView(binding.root)

    ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }
    eventListener()
  }

  private fun eventListener() {

    binding.btnIngresarGenero.setOnClickListener {
      val nombreGenero = binding.txtAgregarGenero.text.toString()
      if (nombreGenero.isNotEmpty()) {
        val nuevoGenero = Genero(0,nombreGenero)
        GeneroRepository.insertGenero(nuevoGenero,
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

}