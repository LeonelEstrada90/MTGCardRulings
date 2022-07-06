package com.gmail.pentominto.us.mtgcardrulings.presentation.searchresults.usecasae

import com.gmail.pentominto.us.mtgcardrulings.utility.Resource
import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.Data
import com.gmail.pentominto.us.mtgcardrulings.data.repository.IDefaultRepository
import javax.inject.Inject

interface IGetSearchResultsUseCase {

    suspend operator fun invoke(input : String): Resource<List<Data>>
}

class GetSearchResultsUseCase @Inject constructor(
    val repository : IDefaultRepository
) : IGetSearchResultsUseCase {

    override suspend fun invoke(input : String) : Resource<List<Data>> {

        val results = repository.getCardsFromApi(input)

        return when (results) {
            is Resource.Success -> Resource.Success(results.data?.data)
            is Resource.Error   -> Resource.Error("Unknown Error")
            else                -> throw Exception()
        }

        //need to handle no internet

    }
}