package com.gmail.pentominto.us.mtgcardrulings.data.model

import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.Legalities

class CardForDisplayScreen(
    val cardName : String?,
    val legalities : Legalities?,
    val layoutType : String?,
    val singleSidedCardLayoutImage : String?,
    val twoSidedCardLayoutFrontImage : String?,
    val twoSidedCardLayoutBackImage : String?,
    // true for front, false for back
    val cardFace : Boolean?

)