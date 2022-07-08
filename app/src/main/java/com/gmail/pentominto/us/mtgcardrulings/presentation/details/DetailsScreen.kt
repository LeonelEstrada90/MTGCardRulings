package com.gmail.pentominto.us.mtgcardrulings.presentation.details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.gmail.pentominto.us.mtgcardrulings.R
import kotlinx.coroutines.delay


@Composable
fun DetailsScreen(card : String) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        content = {

            Column(modifier = Modifier
                .fillMaxSize()
                ) {

                SubcomposeAsyncImage(
                    modifier = Modifier.fillMaxWidth().heightIn().align(CenterHorizontally).padding(8.dp),
                    model = "https://c1.scryfall.com/file/scryfall-cards/png/front/a/a/aa8f58f1-4843-4926-b3c4-98898201c216.png?1562791329",
                    contentDescription = null,
                    loading = {
                        CircularProgressIndicator()
                    }
                )

                Text(
                    text = "erewr"
                )


            }
        }
    )
}