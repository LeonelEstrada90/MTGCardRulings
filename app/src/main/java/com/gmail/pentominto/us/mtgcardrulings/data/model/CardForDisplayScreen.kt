package com.gmail.pentominto.us.mtgcardrulings.data.model

import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.Legalities

class CardForDisplayScreen(
    val cardName : String?,
    val normalCardLayoutImage : String?,
    val legalities : Legalities?,
    val transformFrontImage : String?,
    val transformBackImage : String?

)