package com.gmail.pentominto.us.mtgcardrulings.data.repository

import com.gmail.pentominto.us.mtgcardrulings.utility.Resource
import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.CardSearchResponse
import com.gmail.pentominto.us.mtgcardrulings.data.remote.ScryfallService
import retrofit2.HttpException
import javax.inject.Inject

interface IDefaultRepository {

    suspend fun getCardsFromApi(input : String) : Resource<CardSearchResponse>
}

class DefaultRepository @Inject constructor(
    val service : ScryfallService
) : IDefaultRepository {

    override suspend fun getCardsFromApi(input : String) : Resource<CardSearchResponse> {

        return try {

            val data = service.getCardsWithInput(input)
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