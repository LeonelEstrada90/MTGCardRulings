package com.gmail.pentominto.us.mtgcardrulings.data.model

import androidx.compose.material.Card
import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.CardSearchResponseData

fun CardSearchResponseData.toCardForDisplay() : CardForDisplay {

    return CardForDisplay(
        cardName = name.toString(),
        mainImage = image_uris?.art_crop.toString()
    )
}