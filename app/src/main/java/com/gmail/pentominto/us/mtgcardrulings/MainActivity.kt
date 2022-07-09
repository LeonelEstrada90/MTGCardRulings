package com.gmail.pentominto.us.mtgcardrulings

import android.os.Bundle
import android.util.Log
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

            SearchResultsScreen() { cardName, cardId ->

                navController.navigate("details/${cardName}/${cardId}")
            }

        }

        composable("details/{cardName}/{cardId}", arguments = listOf(
            navArgument("cardName") {type = NavType.StringType},
            navArgument("cardId") {type = NavType.StringType}),) {

            val cardName = remember {
                it.arguments?.getString("cardName")
            }

            val cardId = remember {
                it.arguments?.getString("cardId")
            }

            DetailsScreen(cardName = cardName.toString(), cardId = cardId.toString())
        }

    }
}