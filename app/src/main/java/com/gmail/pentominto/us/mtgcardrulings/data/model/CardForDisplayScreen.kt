package com.gmail.pentominto.us.mtgcardrulings.data.model

import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.Legalities

class CardForDisplayScreen(
    val cardName : String?,
    val legalities : Legalities?,
    val layoutType : String?,
    val normalCardLayoutImage : String?,
    val transformFrontImage : String?,
    val transformBackImage : String?

)