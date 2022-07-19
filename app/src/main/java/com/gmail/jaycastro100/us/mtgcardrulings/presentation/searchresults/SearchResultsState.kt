package com.gmail.jaycastro100.us.mtgcardrulings.presentation.searchresults

import com.gmail.jaycastro100.us.mtgcardrulings.data.model.cardssearchresponse.CardSearchResponseData

data class SearchResultsState(

    val searchResults : List<CardSearchResponseData> = emptyList(),
    val searchQuery : String = "",
    val searchQueryIsBlank : Boolean = true,
    val searchResultIsLoading : Boolean = true,
    val isReadyForSearch : Boolean = true,
    val hasError : Boolean = true,
    val hasData : Boolean = false
)
