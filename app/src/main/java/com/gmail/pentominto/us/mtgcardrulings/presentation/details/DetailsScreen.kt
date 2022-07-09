package com.gmail.pentominto.us.mtgcardrulings.presentation.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.gmail.pentominto.us.mtgcardrulings.R
import com.gmail.pentominto.us.mtgcardrulings.data.model.rulingsresponse.RulingsResponseData


@Composable
fun DetailsScreen(
    viewModel : DetailsScreenViewModel = hiltViewModel(),
    cardName : String,
    cardId : String
) {

    LaunchedEffect(
        key1 = Unit
    ) {

        viewModel.getSingleCardData(
            cardName,
            cardId
        )
    }

    val detailsState = viewModel.detailsState

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        content = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(8.dp)
            ) {

                SubcomposeAsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally)
                        .padding(8.dp),
                    model = detailsState.cardSearchResponseData?.image_uris?.large,
                    contentDescription = null,
                    loading = {
                        CircularProgressIndicator()
                    },
                    error = { painterResource(id = R.drawable.makochan) }

                )

                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    detailsState.rulingsData.forEach { item ->
                        RulingItem(item = item)
                    }
                }
            }
        }
    )
}

@Composable
fun RulingItem(item : RulingsResponseData) {

    Text(
        text = "* ${item.comment.toString()}",
        fontSize = 18.sp
    )
    Spacer(modifier = Modifier.height(4.dp))
}