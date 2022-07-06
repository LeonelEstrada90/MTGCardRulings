package com.gmail.pentominto.us.mtgcardrulings.presentation.searchresults

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gmail.pentominto.us.mtgcardrulings.utility.Resource
import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.Data

@Composable
fun SearchResultsScreen(
    viewModel : SearchResultsViewModel = hiltViewModel(),
    onItemClick : (String) -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        content = {

            var text by rememberSaveable { mutableStateOf("Text") }

            Column(
                modifier =  Modifier.fillMaxWidth()
            ) {

                Row(
                    modifier =  Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = text,
                        onValueChange = {
                            text = it
                        } ,
                        modifier = Modifier.widthIn(),
                        label = { Text(text = "Label")}
                    )

                    Button(
                        modifier = Modifier.widthIn(),
                        onClick = { viewModel.getSearchResults("red") }
                    ) {

                    }
                }

                val listOfCards by remember { viewModel.listOfCards }

                when (val state = listOfCards ) {

                    is Resource.Uninitialized -> {
                        Text(text = "Ready to Search")
                    }

                    is Resource.Loading       -> {
                        Text(text = "Loading")
                    }
                    is Resource.Success       -> {
                        state.data?.let { 
                            CardList(cardList = state.data)
                        }
                    }
                    is Resource.Error         -> {
                        Text(text = "Unknown Error")
                    }
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