package com.gmail.pentominto.us.mtgcardrulings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gmail.pentominto.us.mtgcardrulings.presentation.details.DetailsScreen
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
        startDestination = "search"
    ) {

        composable("search"){

            SearchResultsScreen { cardId ->

                navController.navigate("details/${cardId}")
            }

        }

        composable("details/{cardId}", arguments = listOf(
            navArgument("cardId") {type = NavType.StringType}),) {

            val cardId = remember {
                it.arguments?.getString("cardId")
            }

            DetailsScreen(cardId = cardId.toString())
        }

    }
}