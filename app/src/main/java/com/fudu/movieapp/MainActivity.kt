package com.fudu.movieapp

import MovieSearchAdapter
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fudu.movieapp.ui.moviesearch.MovieSearchViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieSearchViewModel
    private lateinit var movieSearchAdapter: MovieSearchAdapter
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId", "InlinedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()
    }

    private fun initializeView() {
        movieViewModel = ViewModelProvider(this).get(MovieSearchViewModel::class.java)
        recyclerView = findViewById(R.id.moviesSearchRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        movieSearchAdapter = MovieSearchAdapter(emptyList())
        recyclerView.adapter = movieSearchAdapter
        val welcomeText = findViewById<LinearLayout>(R.id.layout_welcome_text)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.progress_bar_anim)
        welcomeText.visibility = View.VISIBLE
        findViewById<Button>(R.id.searchButton).setOnClickListener {
            progressBar.visibility = View.VISIBLE
            progressBar.startAnimation(rotateAnimation)
            val movieName = findViewById<EditText>(R.id.searchEditText).text.toString()
            if (movieName.isNotEmpty() && movieName.length >= 3) {
                movieViewModel.searchMovie(movieName)
            } else {
                disableProgressBar(progressBar)
                welcomeText.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
                Toast.makeText(this, getString(R.string.enter_valid_data), Toast.LENGTH_SHORT)
                    .show()
            }
        }
        setData(welcomeText, progressBar)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setData(welcomeText: LinearLayout, progressBar: ProgressBar) {
        movieViewModel.moviesList.observe(this, Observer { movies ->
            disableWelcomeView(welcomeText)
            disableProgressBar(progressBar)
            movies?.let {
                recyclerView.visibility = View.VISIBLE
                movieSearchAdapter = MovieSearchAdapter(movies)
                recyclerView.adapter = movieSearchAdapter
                movieSearchAdapter.notifyDataSetChanged()
            }
        })
        movieViewModel.error.observe(this, Observer { errorMessage ->
            disableProgressBar(progressBar)
            welcomeText.visibility = View.VISIBLE
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        })
    }

    private fun disableWelcomeView(welcomeText: LinearLayout) {
        welcomeText.visibility = View.GONE
    }

    private fun disableProgressBar(progressBar: ProgressBar) {
        progressBar.clearAnimation()
        progressBar.visibility = View.GONE
    }
}