package com.anime.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anime.app.databinding.ActivityMainBinding
import com.anime.app.model.AnimeQuote
import com.anime.app.recyclerview.AnimeQuoteAdapter
import com.anime.app.repository.AnimeQuoteRepository
import com.anime.app.viewmodel.AnimeQuoteViewModel
import com.anime.app.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AnimeQuoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animeQuoteAdapter = AnimeQuoteAdapter{animeQuote: AnimeQuote ->

            Toast.makeText(
                this@MainActivity,
                animeQuote.quote,
                Toast.LENGTH_SHORT
            ).show() }

        binding.recyclerView.adapter = animeQuoteAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val repository = AnimeQuoteRepository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory)[AnimeQuoteViewModel::class.java]

        viewModel.animeQuoteList.observe(this, Observer { result ->
            result.body()?.let {animeQuotes ->

                animeQuoteAdapter.submitList(animeQuotes)
            }

        })
        binding.fetchResourcesButton.setOnClickListener {
            viewModel.getTenAnimeQuotes()
        }



    }
}

/*
* Resources
* https://animechan.vercel.app/guide#default-quotes
* https://animechan.vercel.app/api/quotes
* https://square.github.io/retrofit/
* https://stackoverflow.com/questions/6054562/how-to-make-the-corners-of-a-button-round
* https://www.workversatile.com/json-to-kotlin-converter
* https://rapidapi.com/search/anime
* https://github.com/jieverson/animeapi
* */