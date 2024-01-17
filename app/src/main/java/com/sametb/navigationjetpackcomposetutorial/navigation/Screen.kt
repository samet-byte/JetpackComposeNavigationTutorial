package com.sametb.navigationjetpackcomposetutorial.navigation


/*
* Navigation Jetpack Compose Tutorial.com.sametb.navigationjetpackcomposetutorial.navigation
* Created by SAMET BAYAT 
* on 17.01.2024 at 11:56 PM
* Copyright (c) 2024 UNITED WORLD. All rights reserved.
*/

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main_screen")
    data object DetailsScreen : Screen("details_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg ->
                append("/$arg")
            }
        }
    }
}