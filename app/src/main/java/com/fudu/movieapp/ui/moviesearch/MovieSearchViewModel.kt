package com.fudu.movieapp.ui.moviesearch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fudu.movieapp.data.model.Movie
import com.fudu.movieapp.data.remote.ApiService
import com.fudu.movieapp.data.remote.RetrofitInstance.retrofit
import com.fudu.movieapp.data.response.MovieResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieSearchViewModel(application: Application) : AndroidViewModel(application) {

    private val _moviesList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>> get() = _moviesList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val movieApiService = retrofit.create(ApiService::class.java)

    fun searchMovie(searchMovieName: String) {
        viewModelScope.launch {
            movieApiService.getSearchMovie(searchMovieName)
                .enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(
                        call: Call<MovieResponse?>,
                        response: Response<MovieResponse?>
                    ) {
                        if (response.isSuccessful && response.body()?.search != null) {
                            _moviesList.value = response.body()?.search
                        } else {
                            _error.value = "No Movies Found"
                        }
                    }

                    override fun onFailure(call: Call<MovieResponse?>, error: Throwable) {
                        _error.value = "Error: ${error.message}"
                    }
                })
        }
    }
}