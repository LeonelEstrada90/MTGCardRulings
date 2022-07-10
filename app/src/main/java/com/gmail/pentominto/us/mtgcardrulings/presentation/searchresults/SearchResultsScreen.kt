@file:OptIn(ExperimentalMaterialApi::class,
    ExperimentalMaterialApi::class
)

package com.gmail.pentominto.us.mtgcardrulings.presentation.searchresults

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.gmail.pentominto.us.mtgcardrulings.ui.theme.BackgroundGray

@Composable
fun SearchResultsScreen(
    viewModel : SearchResultsViewModel = hiltViewModel(),
    onItemClick : (String, String) -> Unit
) {

    val searchState = viewModel.searchState

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundGray),
        content = {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                OutlinedTextField(
                    value = searchState.searchQuery,
                    onValueChange = {
                        viewModel.onSearchQueryChanged(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    singleLine = true,
                    label = {
                        Text(
                            text = "Search...",
                            fontStyle = FontStyle.Italic
                        )
                    }
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

                    if (searchState.searchQuery.isBlank()) {

                        StatusMessage(
                            message = "Ready to Search",
                            messageModifier
                        )
                    } else if (searchState.hasData) {

                        LazyColumn {
                            items(searchState.searchResults) { item ->
                                SingleItem(
                                    cardName = item.name.toString(), item.id.toString(),
                                    thumbnailUrl = item.image_uris?.art_crop.toString(),
                                    onItemClick = { name , id ->
                                        onItemClick(name, id)
                                    }
                                )
                            }
                        }
                    } else if (searchState.isLoading) {

                        StatusMessage(
                            message = "Loading",
                            modifier = messageModifier
                        )
                    } else if (searchState.hasError) {

                        StatusMessage(
                            message = "No Results",
                            messageModifier
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
        modifier = modifier
    )
}

@Composable
fun SingleItem(
    cardName : String,
    cardId : String,
    thumbnailUrl : String,
    onItemClick : (String, String) -> Unit
) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(
                    cardName,
                    cardId
                )
            },
        elevation = 8.dp
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            SubcomposeAsyncImage(
                modifier = Modifier
                    .weight(1f, false)
                    .padding(end = 16.dp)
                    .border(border = BorderStroke(width = 1.dp, Color.Black)
                    ),
                model = thumbnailUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
                )
            Text(
                text = cardName,
                fontSize = 24.sp,
                modifier = Modifier.weight(3f, false)
            )
        }

    }
}