package com.gmail.jaycastro100.us.mtgcardrulings.data.model.cardssearchresponse

data class CardSearchResponse(
    val `data`: List<CardSearchResponseData> = listOf(),
    val has_more: Boolean? = false,
    val next_page: String? = "",
    val `object`: String? = "",
    val total_cards: Int? = 0
)