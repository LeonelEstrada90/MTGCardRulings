package com.gmail.jaycastro100.us.mtgcardrulings.presentation.details

import com.gmail.jaycastro100.us.mtgcardrulings.data.model.rulingsresponse.RulingsResponseData

data class CardDetailsState(
    val infoForDisplay : CardForDisplayScreen? = null,
    val isLoading : Boolean = true,
    val frontSide : Boolean = true
)