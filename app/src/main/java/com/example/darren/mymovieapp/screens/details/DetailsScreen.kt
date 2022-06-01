package com.example.darren.mymovieapp.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.darren.mymovieapp.models.getMovies
import com.example.darren.mymovieapp.screens.home.MainContent
import com.example.darren.mymovieapp.screens.home.MovieRow

@Composable
fun DetailsScreen(navController: NavController, movieId: String?){
    val movie = getMovies().filter {
        it.id == movieId
    }[0]

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.LightGray,
                elevation = 4.dp,) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                }
            }
        }) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                MovieRow(movie)
                Text(
                    text = "Movie Images",
                    style = MaterialTheme.typography.h6
                )
                LazyColumn{
                    items(movie.images){
                        Card(
                            modifier = Modifier
                                .padding(12.dp)
                                .size(300.dp,),
                            elevation = 4.dp
                        ) {
                            AsyncImage(model = it, contentDescription = "")
                        }
                    }
                }

            }
        }
    }



}