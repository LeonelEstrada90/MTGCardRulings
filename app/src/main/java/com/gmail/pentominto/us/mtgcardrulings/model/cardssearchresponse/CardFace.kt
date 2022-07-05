package com.gmail.pentominto.us.mtgcardrulings.model.cardssearchresponse

data class CardFace(
    val artist: String? = "",
    val artist_id: String? = "",
    val color_indicator: List<String>? = listOf(),
    val colors: List<String>? = listOf(),
    val flavor_name: String? = "",
    val flavor_text: String? = "",
    val illustration_id: String? = "",
    val image_uris: ImageUrisX? = ImageUrisX(),
    val mana_cost: String? = "",
    val name: String? = "",
    val `object`: String? = "",
    val oracle_text: String? = "",
    val power: String? = "",
    val toughness: String? = "",
    val type_line: String? = ""
)