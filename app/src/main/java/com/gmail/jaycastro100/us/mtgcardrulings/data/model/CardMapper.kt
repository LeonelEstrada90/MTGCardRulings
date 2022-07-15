package com.gmail.jaycastro100.us.mtgcardrulings.data.model

import com.gmail.jaycastro100.us.mtgcardrulings.data.model.cardssearchresponse.CardSearchResponseData
import com.gmail.jaycastro100.us.mtgcardrulings.data.model.rulingsresponse.RulingsResponseData
import com.gmail.jaycastro100.us.mtgcardrulings.presentation.details.CardForDisplayScreen

fun CardSearchResponseData.toCardForDisplay(
    transformFrontImage : String?,
    transformBackImage : String?,
    layoutType : String?,
    legalities : List<String>,
    rulings : List<RulingsResponseData>?
) : CardForDisplayScreen {

    return CardForDisplayScreen(
        cardName = name,
        singleSidedCardLayoutImage = image_uris?.large,
        legalities = legalities,
        twoSidedCardLayoutBackImage = transformBackImage,
        twoSidedCardLayoutFrontImage = transformFrontImage,
        layoutType = layoutType,
        cardFace = true,
        rulings = rulings
    )
}
