package com.example.libreria.iu.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.libreria.databinding.ActivityDetalleLibroBinding
import com.example.libreria.iu.adapters.LibroAdapter
import com.example.libreria.models.Libro

class Detalle : AppCompatActivity() ,LibroAdapter.OnLibroClickListener{
    private lateinit var binding: ActivityDetalleLibroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleLibroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombre = intent.getStringExtra("nombre")
        val autor = intent.getStringExtra("autor")
        val editorial = intent.getStringExtra("editorial")
        val sinopsis = intent.getStringExtra("sinopsis")
        val isbn = intent.getStringExtra("isbn")
        val calificacion = intent.getDoubleExtra("calificacion", 0.0)
        val imagen = intent.getStringExtra("imagen")

        binding.txtNombreLibro.text = nombre
        binding.txtAutor.text = "Autor: $autor"
        binding.txtEditorial.text = "Editorial: $editorial"
        binding.txtSinopsis.text = "Sinopsis: $sinopsis"
        binding.txtISBN.text = "ISBN: $isbn"
        binding.txtCalificacion.text = "Calificación: $calificacion"

        Glide.with(this)
            .load(imagen)
            .into(binding.imgLibro)

        editar()

    }

    private fun editar() {
        TODO("Not yet implemented")
    }

    override fun onLibroClick(libro: Libro) {
        TODO("Not yet implemented")
    }

    override fun onLibroDeleteClick(libro: Libro) {
        TODO("Not yet implemented")
    }

    override fun editarLibro(libro: Libro) {


    }
}
