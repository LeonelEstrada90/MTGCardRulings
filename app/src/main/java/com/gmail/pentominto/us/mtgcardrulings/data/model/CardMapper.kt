package com.gmail.pentominto.us.mtgcardrulings.data.model

import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.CardSearchResponseData

fun CardSearchResponseData.toCardForDisplay(transformFrontImage : String?, transformBackImage : String?, layoutType : String?) : CardForDisplayScreen {

    return CardForDisplayScreen(
        cardName = name,
        normalCardLayoutImage = image_uris?.large,
        legalities = legalities,
        transformBackImage = transformBackImage,
        transformFrontImage = transformFrontImage,
        layoutType = layoutType,
        cardFace = true
    )
}
