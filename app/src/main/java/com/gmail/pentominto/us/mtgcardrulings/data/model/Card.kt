package com.gmail.pentominto.us.mtgcardrulings.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class Card(
    @PrimaryKey() val id: String,
    val cardName : String
)

//fun Data.convertToCard() : Card {
//
//    return Card(
//        id = this.id,
//        cardName = this.name
//    )
//}
