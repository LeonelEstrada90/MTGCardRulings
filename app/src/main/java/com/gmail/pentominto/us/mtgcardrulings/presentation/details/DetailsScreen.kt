package com.gmail.pentominto.us.mtgcardrulings.presentation.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.gmail.pentominto.us.mtgcardrulings.R


@Composable
fun DetailsScreen(
    viewModel : DetailsScreenViewModel = hiltViewModel(),
    cardName : String)
{

    LaunchedEffect(
        key1 = Unit
    ) {

        viewModel.getSingleCardData(cardName)
    }

    val detailsState = viewModel.detailsState

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        content = {

            Column(modifier = Modifier
                .fillMaxSize()
                ) {

                SubcomposeAsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn()
                        .align(CenterHorizontally)
                        .padding(8.dp),
                    model = detailsState.cardSearchResponseData?.image_uris?.normal,
                    contentDescription = null,
                    loading = {
                        CircularProgressIndicator()
                    },
                    error = { painterResource(id = R.drawable.makochan) }

                )

                detailsState.cardSearchResponseData?.rulings_uri?.let { it ->
                    Text(
                        text = it
                    )
                }


            }
        }
    )
}