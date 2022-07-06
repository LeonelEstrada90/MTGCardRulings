package com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse

data class CardSearchResponse(
    val `data`: List<Data> = listOf(),
    val has_more: Boolean? = false,
    val next_page: String? = "",
    val `object`: String? = "",
    val total_cards: Int? = 0
)