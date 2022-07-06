package com.gmail.pentominto.us.mtgcardrulings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gmail.pentominto.us.mtgcardrulings.presentation.searchresults.SearchResultsScreen
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

            SearchResultsScreen() { selected ->

//                navController.navigate("")
            }

        }
    }
}