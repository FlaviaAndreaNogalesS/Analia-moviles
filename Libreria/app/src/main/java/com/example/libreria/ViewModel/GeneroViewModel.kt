package com.example.libreria.ViewModel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libreria.iu.adapters.GeneroAdapter
import com.example.libreria.databinding.ActivityGenerosBinding
import com.example.libreria.iu.activity.LibrosPorGeneroActivity
import com.example.libreria.models.Genero
import com.example.libreria.repositories.GeneroRepository

class GeneroViewModel : AppCompatActivity(), GeneroAdapter.OnGeneroClickListener {

    private lateinit var binding: ActivityGenerosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenerosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewGeneros.layoutManager = LinearLayoutManager(this)

        fetchGeneros()
    }

    private fun fetchGeneros() {
        GeneroRepository.getGeneros(
            success = { generos ->
                generos?.let {
                    binding.recyclerViewGeneros.adapter = GeneroAdapter(it, this)
                }
            },
            failure = {
                it.printStackTrace()
            })
    }

    override fun onGeneroClick(genero: Genero) {
        val intent = Intent(this, LibrosPorGeneroActivity::class.java).apply {
            putExtra("generoId", genero.id)
        }
        startActivity(intent)
    }

    override fun onDeleteGeneroClick(genero: Genero) {
        TODO("Not yet implemented")
    }

    override fun onEditGeneroClick(genero: Genero) {

    }

}
