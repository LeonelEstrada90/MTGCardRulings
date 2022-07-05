package com.gmail.pentominto.us.mtgcardrulings.repository

import com.gmail.pentominto.us.mtgcardrulings.Resource
import com.gmail.pentominto.us.mtgcardrulings.db.FavoritesDao
import com.gmail.pentominto.us.mtgcardrulings.model.Card
import com.gmail.pentominto.us.mtgcardrulings.model.cardssearchresponse.CardSearchResponse
import com.gmail.pentominto.us.mtgcardrulings.model.cardssearchresponse.Data
import com.gmail.pentominto.us.mtgcardrulings.service.ScryfallService
import retrofit2.Retrofit
import javax.inject.Inject

interface IDefaultRepository {

    suspend fun getCardsFromApi(input : String) : Resource<List<Data>>
}

class DefaultRepository @Inject constructor(
    val retrofit : Retrofit
) : IDefaultRepository {

    override suspend fun getCardsFromApi(input : String) : Resource<List<Data>> {

        return try {

            val response = retrofit.create(ScryfallService::class.java)
            val data = response.getCardsWithInput(input).data
            Resource.Success(data = data)

        } catch (e : Exception) {

            Resource.Error("Error getting search results.")
        }
    }
}