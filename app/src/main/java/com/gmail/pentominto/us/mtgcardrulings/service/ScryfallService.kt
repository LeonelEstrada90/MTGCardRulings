package com.gmail.pentominto.us.mtgcardrulings.service

import com.gmail.pentominto.us.mtgcardrulings.model.cardssearchresponse.CardSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ScryfallService {

    @GET("cards/search")
    suspend fun getCardsWithInput(@Query("q") input : String): CardSearchResponse
}