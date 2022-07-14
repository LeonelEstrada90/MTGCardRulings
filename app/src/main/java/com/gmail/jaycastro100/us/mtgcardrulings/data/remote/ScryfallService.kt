package com.gmail.jaycastro100.us.mtgcardrulings.data.remote

import com.gmail.jaycastro100.us.mtgcardrulings.data.model.cardssearchresponse.CardSearchResponse
import com.gmail.jaycastro100.us.mtgcardrulings.data.model.cardssearchresponse.CardSearchResponseData
import com.gmail.jaycastro100.us.mtgcardrulings.data.model.rulingsresponse.RulingsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ScryfallService {

    @GET("cards/search")
    suspend fun getSearchResultsList(@Query("q") input : String): CardSearchResponse

    @GET("cards/{id}")
    suspend fun getSingleCardData(@Path("id") id : String) : CardSearchResponseData

    @GET("cards/{id}/rulings")
    suspend fun getRulingsData(@Path("id") id : String) : RulingsResponse

}