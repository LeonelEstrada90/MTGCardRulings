package com.gmail.jaycastro100.us.mtgcardrulings.presentation.searchresults.usecase

import com.gmail.jaycastro100.us.mtgcardrulings.utility.Resource
import com.gmail.jaycastro100.us.mtgcardrulings.data.model.cardssearchresponse.CardSearchResponseData
import com.gmail.jaycastro100.us.mtgcardrulings.data.repository.IDefaultRepository
import javax.inject.Inject

interface IGetSearchResultsUseCase {

    suspend operator fun invoke(input : String): Resource<List<CardSearchResponseData>>
}

class GetSearchResultsUseCase @Inject constructor(
    private val repository : IDefaultRepository
) : IGetSearchResultsUseCase {

    override suspend fun invoke(input : String) : Resource<List<CardSearchResponseData>> {

        val results = repository.getCardsFromApi(input)

        return when (results) {
            is Resource.Success -> Resource.Success(results.data?.data)
            is Resource.Error   -> Resource.Error("Unknown Error")
            else                -> throw Exception()
        }
    }
}