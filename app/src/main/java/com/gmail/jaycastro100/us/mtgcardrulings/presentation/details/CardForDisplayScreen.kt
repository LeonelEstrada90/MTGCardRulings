package com.gmail.jaycastro100.us.mtgcardrulings.presentation.details

import com.gmail.jaycastro100.us.mtgcardrulings.data.model.rulingsresponse.RulingsResponseData

class CardForDisplayScreen(
    val cardName : String?,
    val legalities : List<String>?,
    val rulings : List<RulingsResponseData>?,
    val layoutType : String?,
    val singleSidedCardLayoutImage : String?,
    val twoSidedCardLayoutFrontImage : String?,
    val twoSidedCardLayoutBackImage : String?,
    // true for front, false for back
    val cardFace : Boolean?

)