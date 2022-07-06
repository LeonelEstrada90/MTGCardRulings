@file:OptIn(ExperimentalMaterialApi::class,
    ExperimentalMaterialApi::class
)

package com.gmail.pentominto.us.mtgcardrulings.presentation.searchresults

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.Data

@Composable
fun SearchResultsScreen(
    viewModel : SearchResultsViewModel = hiltViewModel(),
    onItemClick : (String) -> Unit
) {

    val searchState = viewModel.searchState

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
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

                        CardList(
                            cardList = searchState.searchResults
                        )

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
        },

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
fun CardList(cardList : List<Data>) {

    LazyColumn {

        items(cardList) { item ->
            SingleItem(
                title = item.name.toString()
            )
        }
    }
}

@Composable
fun SingleItem(
    title : String,
) {

    val focusManager = LocalFocusManager.current

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        onClick = { focusManager.clearFocus() },
        elevation = 8.dp
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = title,
                fontSize = 24.sp
            )
        }

    }
}