package com.gmail.pentominto.us.mtgcardrulings.presentation.details

import com.gmail.pentominto.us.mtgcardrulings.data.model.CardForDisplayScreen
import com.gmail.pentominto.us.mtgcardrulings.data.model.rulingsresponse.RulingsResponseData

data class CardDetailsState(
    val infoForDisplay : CardForDisplayScreen? = null,
    val rulingsData : List<RulingsResponseData> = listOf(),
    val isLoading : Boolean = true,
    val frontSide : Boolean = true
)