package com.example.libreria.iu.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.libreria.databinding.GeneroItemLayoutBinding
import com.example.libreria.models.Genero

class GeneroAdapter(private val generos: List<Genero>,private val listener: OnGeneroClickListener) : RecyclerView.Adapter<GeneroAdapter.GeneroViewHolder>() {

    interface OnGeneroClickListener {
        fun onGeneroClick(genero: Genero)
        fun onDeleteGeneroClick(genero: Genero)
        fun onEditGeneroClick(genero: Genero)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneroViewHolder {
        val binding = GeneroItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GeneroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GeneroViewHolder, position: Int) {
        val genero = generos[position]
        holder.bind(genero, listener)
    }

    override fun getItemCount(): Int = generos.size

    class GeneroViewHolder(private val binding: GeneroItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genero: Genero, listener: OnGeneroClickListener) {
            binding.txtNameGen.text = genero.nombre
            binding.root.setOnClickListener {
                listener.onGeneroClick(genero)
            }
            binding.editarGenero.setOnClickListener {
                listener.onEditGeneroClick(genero)
            }
            binding.eliminarGEnero.setOnClickListener {
                listener.onDeleteGeneroClick(genero)


            }
        }
    }
}
