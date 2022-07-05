package com.gmail.pentominto.us.mtgcardrulings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gmail.pentominto.us.mtgcardrulings.favorites.FavoritesScreen
import com.gmail.pentominto.us.mtgcardrulings.ui.theme.MTGCardRulingsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MTGCardRulingsTheme {
                // A surface container using the 'background' color from the theme
                MTGRulingsApp()
            }
        }
    }
}

@Composable
fun MTGRulingsApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "favorites"
    ) {

        composable("favorites"){

            FavoritesScreen() { selected ->

//                navController.navigate("")
            }

        }
    }
}