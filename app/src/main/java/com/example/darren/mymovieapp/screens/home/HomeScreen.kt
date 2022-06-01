package com.example.darren.mymovieapp.screens.home

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.darren.mymovieapp.navigation.MovieScreens
import com.example.darren.mymovieapp.models.Movie
import com.example.darren.mymovieapp.models.getMovies

@Composable
fun HomeScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Magenta,
                elevation = 4.dp) {
                Text(text = "Movies")
            }
        }) {
        MainContent(navController = navController)
    }
}

@Composable
fun MainContent(movieList: List<Movie> = getMovies(),
                navController: NavController
)
{
    LazyColumn{
        items(movieList){
            MovieRow(movie = it){
                movie ->
                navController.navigate(route = MovieScreens.DetailsScreens.name + "/${movie}")
            }
        }
    }
}

@Preview
@Composable
fun MovieRow(movie: Movie = getMovies()[0], onItemClick: (String) -> Unit = {}){

    val expandedState = remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .clickable {
            onItemClick(movie.id)
        },
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                elevation = 4.dp
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .build()
                        ,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )

            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = "Released: ${movie.year}",
                    style = MaterialTheme.typography.caption
                )

                AnimatedVisibility(visible = expandedState.value) {
                    Column {
                        Text(
                            text = "Rating: ${movie.rating}",
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            text = "Actors: ${movie.actors}",
                            style = MaterialTheme.typography.caption
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Divider(thickness = 0.8.dp)
                        Text(
                            buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray,
                                fontSize = 13.sp)
                            ) {
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light)
                            ) {
                                append(movie.plot)
                            }

                        }, modifier = Modifier.padding(6.dp))
                    }
                }

                Icon(
                    imageVector =
                        if (expandedState.value){
                            Icons.Filled.KeyboardArrowUp
                        }else{
                             Icons.Filled.KeyboardArrowDown
                             },
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expandedState.value = !expandedState.value
                        }
                        .align(alignment = Alignment.CenterHorizontally)
                    ,
                    tint = Color.Gray
                )
            }
        }
    }
}