package com.example.darren.mymovieapp.navigation

enum class MovieScreens{
    HomeScreen,
    DetailsScreens;
    companion object{
        fun fromRoute(route: String?): MovieScreens
        = when (route?.substringBefore("/")){
            HomeScreen.name -> HomeScreen
            DetailsScreens.name -> DetailsScreens
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}