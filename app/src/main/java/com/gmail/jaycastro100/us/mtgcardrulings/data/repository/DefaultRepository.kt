package com.gmail.jaycastro100.us.mtgcardrulings.data.repository

import com.gmail.jaycastro100.us.mtgcardrulings.utility.Resource
import com.gmail.jaycastro100.us.mtgcardrulings.data.model.cardssearchresponse.CardSearchResponse
import com.gmail.jaycastro100.us.mtgcardrulings.data.model.cardssearchresponse.CardSearchResponseData
import com.gmail.jaycastro100.us.mtgcardrulings.data.model.rulingsresponse.RulingsResponse
import com.gmail.jaycastro100.us.mtgcardrulings.data.remote.ScryfallService
import retrofit2.HttpException
import javax.inject.Inject

interface IDefaultRepository {

    suspend fun getCardsFromApi(input : String) : Resource<CardSearchResponse>

    suspend fun getCardInfo(cardName : String) : Resource<CardSearchResponseData>

    suspend fun getRulingsData(id : String) : Resource<RulingsResponse>
}

class DefaultRepository @Inject constructor(
    private val service : ScryfallService
) : IDefaultRepository {

    override suspend fun getCardsFromApi(input : String) : Resource<CardSearchResponse> {

        return try {

            val data = service.getSearchResultsList(input)
            Resource.Success(data = data)
        } catch (e : HttpException) {
            e.printStackTrace()
            Resource.Error("Network Error")
        } catch (e : Exception) {
            e.printStackTrace()
            Resource.Error("Unknown Error")
        }
    }

    override suspend fun getCardInfo(cardName : String) : Resource<CardSearchResponseData> {

        return try {

            val data = service.getSingleCardData(cardName)
            Resource.Success(data = data)
        } catch (e : HttpException) {
            e.printStackTrace()
            Resource.Error("Network Error")
        } catch (e : Exception) {
            e.printStackTrace()
            Resource.Error("Unknown Error")
        }
    }

    override suspend fun getRulingsData(id : String) : Resource<RulingsResponse> {

        return try {

            val data = service.getRulingsData(id)
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