package com.gmail.pentominto.us.mtgcardrulings.presentation.searchresults

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gmail.pentominto.us.mtgcardrulings.utility.Resource
import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.Data
import org.intellij.lang.annotations.JdkConstants

@Composable
fun SearchResultsScreen(
    viewModel : SearchResultsViewModel = hiltViewModel(),
    onItemClick : (String) -> Unit
) {

    val searchState = viewModel.searchState

    val listOfCards by remember { viewModel.listOfCardsState }

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        content = {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                OutlinedTextField(
                    value = searchState,
                    onValueChange = {
                        viewModel.getNewResults(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    label = { Text(text = "Enter Card Name") }
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn()
                        .align(alignment = CenterHorizontally)
                ) {

                    val centerModifier = Modifier.align(Center)

                    val state = listOfCards

                    if (state is Resource.Uninitialized) {

                        Text(text = "Ready to Search")
                    } else if (state is Resource.Success) {

                        state.data?.let {
                            CardList(cardList = state.data)
                        }
                    } else if (state is Resource.Loading) {

                        Text(text = "Loading")
                    } else if (state is Resource.Error && searchState.isBlank()){

                        Text(text = "Ready to Search")
                    } else if (state is Resource.Error) {

                        Text(text = "No results")
                    }

//                    when (val state = listOfCards) {
//
//                        is Resource.Uninitialized -> {
//                            Text(text = "Ready to Search")
//                        }
//
//                        is Resource.Loading       -> {
//
//                        }
//                        is Resource.Success       -> {
//                            state.data?.let {
//                                CardList(cardList = state.data)
//                            }
//                        }
//                        is Resource.Error -> {
//                            Text(text = "No results")
//                        }
//                    }
                }
            }
        },

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

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
//            .clickable { onItemClick(title) },
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