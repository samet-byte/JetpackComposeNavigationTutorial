package com.sametb.navigationjetpackcomposetutorial.navigation

import android.provider.SyncStateContract.Constants
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sametb.navigationjetpackcomposetutorial.constants.*
import com.sametb.navigationjetpackcomposetutorial.screens.DetailsScreen
import com.sametb.navigationjetpackcomposetutorial.screens.MainScreen


/*
* Navigation Jetpack Compose Tutorial.com.sametb.navigationjetpackcomposetutorial.navigation
* Created by SAMET BAYAT 
* on 18.01.2024 at 12:12 AM
* Copyright (c) 2024 UNITED WORLD. All rights reserved.
*/



@Composable
fun Navigation() {
    val navigationController = rememberNavController()
    
    NavHost(
        navController = navigationController, 
        startDestination = Screen.MainScreen.route
    ){
        composable(
            route = Screen.MainScreen.route
        ){
            MainScreen(navController = navigationController)
        }
        composable(
            route = "${Screen.DetailsScreen.route}/{${Keys.USER}}", // /{otherArg}
//            route = "${Screen.DetailsScreen.route}?name=${Keys.NAME}}" // /{otherArg}
            arguments = listOf(
                navArgument(Keys.USER){
                    type = NavType.StringType
                    defaultValue = Default.DEFAULT_USER
                    nullable = true
                }
            )
        ){entry ->
            DetailsScreen(name = entry.arguments?.getString(Keys.USER))
        }
    }
}



