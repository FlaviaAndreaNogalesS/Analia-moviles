package com.example.libreria.repositories

import com.example.libreria.API.APILibreria
import com.example.libreria.models.Genero
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GeneroRepository {
    private val apiService = RetrofitRepository.getRetrofitInstance().create(APILibreria::class.java)
    fun getGeneros(success: (List<Genero>?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibreria = retrofit.create(APILibreria::class.java)
        service.getGeneros().enqueue(object : Callback<List<Genero>> {
            override fun onResponse(call: Call<List<Genero>>, response: Response<List<Genero>>) {
                success(response.body())
            }

            override fun onFailure(call: Call<List<Genero>>, t: Throwable) {
                failure(t)
            }
        })
    }
    fun updateGenero(id: Int, genero: Genero, success: (Genero?) -> Unit, failure: (Throwable) -> Unit) {
        apiService.updateGenero(genero, id).enqueue(object : Callback<Genero> {
            override fun onResponse(call: Call<Genero>, response: Response<Genero>) {
                if (response.isSuccessful) {
                    success(response.body())
                } else {
                    failure(Throwable(response.message()))
                }
            }

            override fun onFailure(call: Call<Genero>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun deleteGenero(id: Int, success: () -> Unit, failure: (Throwable) -> Unit) {
        apiService.deleteGenero(id).enqueue(object : Callback<Void> {
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

    fun insertGenero(genero: Genero, success: (Genero?) -> Unit, failure: (Throwable) -> Unit) {
        apiService.insertGenero(genero).enqueue(object : Callback<Genero> {
            override fun onResponse(call: Call<Genero>, response: Response<Genero>) {
                if (response.isSuccessful) {
                    success(response.body())
                } else {
                    failure(Throwable(response.message()))
                }
            }

            override fun onFailure(call: Call<Genero>, t: Throwable) {
                failure(t)
            }
        })
    }

}
