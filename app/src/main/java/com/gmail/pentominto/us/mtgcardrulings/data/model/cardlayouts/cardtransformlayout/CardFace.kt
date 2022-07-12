package com.gmail.pentominto.us.mtgcardrulings.data.model.cardlayouts.cardtransformlayout

data class CardFace(
    val artist: String = "",
    val artist_id: String = "",
    val color_indicator: List<String>? = null,
    val colors: List<String> = listOf(),
    val flavor_name: String? = null,
    val illustration_id: String = "",
    val image_uris: ImageUris = ImageUris(),
    val mana_cost: String = "",
    val name: String = "",
    val `object`: String = "",
    val oracle_text: String = "",
    val power: String? = null,
    val toughness: String? = null,
    val type_line: String = ""
)