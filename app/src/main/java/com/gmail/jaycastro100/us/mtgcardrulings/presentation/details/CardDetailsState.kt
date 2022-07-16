package com.gmail.jaycastro100.us.mtgcardrulings.presentation.details

data class CardDetailsState(
    val infoForDisplay : CardForDisplayScreen? = null,
    val isLoading : Boolean = true,
    val hasError : Boolean = true,

    //only for 2 sided cards, front is true back is false
    val frontSide : Boolean = true
)