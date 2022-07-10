package com.gmail.pentominto.us.mtgcardrulings.presentation.details

import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.CardSearchResponseData
import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.Legalities
import com.gmail.pentominto.us.mtgcardrulings.data.model.rulingsresponse.RulingsResponseData

data class CardDetailsState(
    val cardSearchResponseData : CardSearchResponseData? = null,
    val rulingsData : List<RulingsResponseData> = listOf(),
    val isLoading : Boolean = true,
    val legalities : List<Pair<String, String>> = listOf()
)