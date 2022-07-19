package com.gmail.jaycastro100.us.mtgcardrulings.presentation.searchresults

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.gmail.jaycastro100.us.mtgcardrulings.ui.theme.LightBlue
import com.gmail.jaycastro100.us.mtgcardrulings.ui.theme.TextGray

@Composable
fun SearchResultsScreen(
    viewModel : SearchResultsViewModel = hiltViewModel(),
    onItemClick : (String) -> Unit
) {

    val searchState by remember { mutableStateOf(viewModel.searchState) }

    Scaffold(
        modifier = Modifier
            .fillMaxWidth(),
        content = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {

                TextField(
                    value = searchState.value.searchQuery,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn()
                        .padding(8.dp),

                    colors = TextFieldDefaults.textFieldColors(
                        textColor = TextGray,
                        disabledTextColor = Color.Transparent,
                        backgroundColor = LightBlue,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent

                    ),
                    onValueChange = {
                        viewModel.onSearchQueryChanged(it)
                    },
                    singleLine = true,
                    label = {
                        Text(
                            text = "Search...",
                            fontStyle = FontStyle.Italic,
                            color = Color.White
                        )
                    },
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {

                    val messageModifier = Modifier
                        .fillMaxWidth()
                        .heightIn()
                        .align(Center)

                    if (searchState.value.searchQuery.isBlank()) {

                        StatusMessage(
                            message = "Ready to Search",
                            modifier = messageModifier
                        )
                    } else if (searchState.value.hasData) {

                        LazyColumn() {
                            items(
                                searchState.value.searchResults,
                                key = { item -> item.id.toString() }) { item ->

                                SingleItem(
                                    cardName = item.name.toString(),
                                    cardId = item.id.toString(),
                                    thumbnailUrl = when (item.layout) {
                                        "transform" -> {
                                            item.card_faces?.get(0)?.image_uris?.art_crop.toString()
                                        }
                                        "modal_dfc" -> {
                                            item.card_faces?.get(0)?.image_uris?.art_crop.toString()
                                        }
                                        else        -> {
                                            item.image_uris?.art_crop.toString()
                                        }
                                    },
                                    onItemClick = { id ->
                                        onItemClick(id)
                                    },
                                    modifier = Modifier
                                )
                            }
                        }
                    } else if (searchState.value.searchResultIsLoading) {

                        StatusMessage(
                            message = "Loading",
                            modifier = messageModifier
                        )
                    } else if (searchState.value.hasError) {

                        StatusMessage(
                            message = "No Results",
                            modifier = messageModifier
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun StatusMessage(message : String, modifier : Modifier) {

    Text(
        text = message,
        textAlign = TextAlign.Center,
        modifier = modifier,
        color = TextGray,
        fontSize = 40.sp
    )
}

@Composable
fun SingleItem(
    cardName : String,
    cardId : String,
    thumbnailUrl : String,
    modifier : Modifier,
    onItemClick : (String) -> Unit
) {

    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(70.dp)
            .clickable {
                onItemClick(cardId)
            },
        elevation = 8.dp
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            SubcomposeAsyncImage(
                modifier = Modifier
                    .weight(
                        1f,
                        false
                    )
                    .fillMaxHeight()
                    .widthIn()
                    .border(
                        border = BorderStroke(
                            width = 0.dp,
                            Color.Transparent
                        )
                    ),
                model = thumbnailUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Text(
                text = cardName,
                fontSize = 24.sp,
                modifier = Modifier
                    .weight(
                        3f,
                        false
                    )
                    .padding(start = 8.dp),
                maxLines = 2
            )
        }

    }
}