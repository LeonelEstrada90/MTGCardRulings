package com.gmail.jaycastro100.us.mtgcardrulings.presentation.details

data class CardDetailsState(
    val screenInfo : CardForDisplayScreen? = null,
    val loading : Boolean = true,
    val error : Boolean = true,

    //only for 2 sided cards, front is true back is false
    val isShowingFrontSide : Boolean = true
)