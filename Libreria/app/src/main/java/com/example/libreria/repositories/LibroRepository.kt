package com.example.libreria.repositories

import com.example.libreria.API.APILibreria
import com.example.libreria.models.Libro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LibroRepository {
    private val retrofit = RetrofitRepository.getRetrofitInstance()
    private val apiService = retrofit.create(APILibreria::class.java)

    fun getLibros(success: (List<Libro>?) -> Unit, failure: (Throwable) -> Unit) {
        apiService.getLibros().enqueue(object : Callback<List<Libro>> {
            override fun onResponse(call: Call<List<Libro>>, response: Response<List<Libro>>) {
                success(response.body())
            }

            override fun onFailure(call: Call<List<Libro>>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun getLibroById(id: Int, success: (Libro?) -> Unit, failure: (Throwable) -> Unit) {
        apiService.getLibroById(id).enqueue(object : Callback<Libro> {
            override fun onResponse(call: Call<Libro>, response: Response<Libro>) {
                success(response.body())
            }

            override fun onFailure(call: Call<Libro>, t: Throwable) {
                failure(t)
            }
        })

    }


    fun deleteLibro(id: Int, success: () -> Unit, failure: (Throwable) -> Unit) {
        apiService.deleteLibro(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    success()
                } else {
                    failure(Throwable(response.message()))
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                failure(t)
            }
        })
    }
}
