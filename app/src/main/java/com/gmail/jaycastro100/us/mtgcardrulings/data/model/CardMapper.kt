package com.gmail.jaycastro100.us.mtgcardrulings.data.model

import com.gmail.jaycastro100.us.mtgcardrulings.data.model.cardssearchresponse.CardSearchResponseData

fun CardSearchResponseData.toCardForDisplay(transformFrontImage : String?, transformBackImage : String?, layoutType : String?, legalities : List<String>) : CardForDisplayScreen {

    return CardForDisplayScreen(
        cardName = name,
        singleSidedCardLayoutImage = image_uris?.large,
        legalities = legalities,
        twoSidedCardLayoutBackImage = transformBackImage,
        twoSidedCardLayoutFrontImage = transformFrontImage,
        layoutType = layoutType,
        cardFace = true
    )
}
