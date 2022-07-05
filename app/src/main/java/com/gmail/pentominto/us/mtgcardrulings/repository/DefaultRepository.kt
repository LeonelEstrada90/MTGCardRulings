package com.gmail.pentominto.us.mtgcardrulings.repository

import android.accounts.NetworkErrorException
import com.gmail.pentominto.us.mtgcardrulings.Resource
import com.gmail.pentominto.us.mtgcardrulings.db.FavoritesDao
import com.gmail.pentominto.us.mtgcardrulings.model.Card
import com.gmail.pentominto.us.mtgcardrulings.model.cardssearchresponse.CardSearchResponse
import com.gmail.pentominto.us.mtgcardrulings.model.cardssearchresponse.Data
import com.gmail.pentominto.us.mtgcardrulings.service.ScryfallService
import retrofit2.HttpException
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

interface IDefaultRepository {

    suspend fun getCardsFromApi(input : String) : Resource<List<Data>>
}

class DefaultRepository @Inject constructor(
    val service : ScryfallService
) : IDefaultRepository {

    override suspend fun getCardsFromApi(input : String) : Resource<List<Data>> {

        return try {

            val data = service.getCardsWithInput(input).data
            Resource.Success(data = data)

        } catch (e : HttpException) {
            e.printStackTrace()
            Resource.Error("Network Error")
        } catch (e : Exception) {
            e.printStackTrace()
            Resource.Error("Unknown Error")
        }
    }
}