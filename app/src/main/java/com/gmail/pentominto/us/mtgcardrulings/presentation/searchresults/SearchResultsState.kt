package com.gmail.pentominto.us.mtgcardrulings.presentation.searchresults

import com.gmail.pentominto.us.mtgcardrulings.data.model.Card
import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.Data

data class SearchResultsState(

    val searchResults : List<Data> = emptyList(),
    val searchQuery : String = "",
    val isBlank : Boolean = true,
    val isLoading : Boolean = true,
    val isReadyForSearch : Boolean = true,
    val hasError : Boolean = true,
    val hasData : Boolean = false
)
