package com.gmail.jaycastro100.us.mtgcardrulings.data.model

class CardForDisplayScreen(
    val cardName : String?,
    val legalities : List<String>?,
    val layoutType : String?,
    val singleSidedCardLayoutImage : String?,
    val twoSidedCardLayoutFrontImage : String?,
    val twoSidedCardLayoutBackImage : String?,
    // true for front, false for back
    val cardFace : Boolean?

)