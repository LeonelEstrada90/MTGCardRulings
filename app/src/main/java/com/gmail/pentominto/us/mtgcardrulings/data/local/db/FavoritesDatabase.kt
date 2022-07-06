package com.gmail.pentominto.us.mtgcardrulings.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gmail.pentominto.us.mtgcardrulings.data.model.Card

@Database(entities = [Card::class], version = 1, exportSchema = false)
abstract class FavoritesDatabase : RoomDatabase() {

    abstract fun getFavoritesDao() : FavoritesDao
}