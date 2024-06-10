package com.example.libreria.iu.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.libreria.databinding.ActivityLibreriaInicioBinding

class libreriaInicio : AppCompatActivity() {

  private lateinit var binding: ActivityLibreriaInicioBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityLibreriaInicioBinding.inflate(layoutInflater)
    setContentView(binding.root)

    stupEvents()

  }

  private fun stupEvents() {
    binding.btngenero.setOnClickListener {
      val intent = Intent(this, listaGenero::class.java)
      startActivity(intent)
    }

    binding.btnLibros.setOnClickListener {
      val intent = Intent(this, listaLibros::class.java)
      startActivity(intent)
    }
  }
}