package com.gmail.pentominto.us.mtgcardrulings.data.model.cardlayouts.splitlayout

data class CardFace(
    val artist: String = "",
    val artist_id: String = "",
    val flavor_name: String? = null,
    val illustration_id: String? = null,
    val mana_cost: String = "",
    val name: String = "",
    val `object`: String = "",
    val oracle_text: String = "",
    val type_line: String = "",
    val watermark: String = ""
)