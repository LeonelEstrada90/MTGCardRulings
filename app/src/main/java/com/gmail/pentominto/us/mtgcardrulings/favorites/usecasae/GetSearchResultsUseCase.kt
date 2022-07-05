package com.gmail.pentominto.us.mtgcardrulings.favorites.usecasae

import com.gmail.pentominto.us.mtgcardrulings.Resource
import com.gmail.pentominto.us.mtgcardrulings.model.cardssearchresponse.CardSearchResponse
import com.gmail.pentominto.us.mtgcardrulings.model.cardssearchresponse.Data
import com.gmail.pentominto.us.mtgcardrulings.repository.IDefaultRepository
import javax.inject.Inject

interface IGetSearchResultsUseCase {

    suspend operator fun invoke(input : String): Resource<List<Data>>
}

class GetSearchResultsUseCase @Inject constructor(
    val repository : IDefaultRepository
) : IGetSearchResultsUseCase {

    override suspend fun invoke(input : String) : Resource<List<Data>> {

        val results = repository.getCardsFromApi(input)
        return Resource.Success(results.data)
        //need to handle no internet

    }
}