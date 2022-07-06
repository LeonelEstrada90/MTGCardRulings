package com.gmail.pentominto.us.mtgcardrulings.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gmail.pentominto.us.mtgcardrulings.data.model.Card

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM cards")
    suspend fun getFavoritesList(): List<Card>

    @Insert
    suspend fun saveCardToFavorites(card : Card)
}