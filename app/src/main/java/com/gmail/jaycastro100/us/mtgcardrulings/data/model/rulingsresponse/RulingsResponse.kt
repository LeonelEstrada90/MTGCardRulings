package com.gmail.jaycastro100.us.mtgcardrulings.data.model.rulingsresponse

data class RulingsResponse(
    val `data`: List<RulingsResponseData> = listOf(),
    val has_more: Boolean? = null,
    val `object`: String? = ""
)