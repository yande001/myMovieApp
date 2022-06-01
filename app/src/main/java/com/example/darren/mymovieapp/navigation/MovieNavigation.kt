package com.example.darren.mymovieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.darren.mymovieapp.screens.details.DetailsScreen
import com.example.darren.mymovieapp.screens.home.HomeScreen

@Composable
fun MovieNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name){
        composable(route = MovieScreens.HomeScreen.name){
            HomeScreen(navController = navController)
        }
        composable(
            route = MovieScreens.DetailsScreens.name + "/{movie}",
            arguments = listOf(navArgument(name = "movie") {type = NavType.StringType})
        ){
            backstackEntry ->
            DetailsScreen(navController = navController, backstackEntry.arguments?.getString("movie"))
        }
    }
}