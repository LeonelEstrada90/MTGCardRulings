package com.gmail.pentominto.us.mtgcardrulings.presentation.details.usecase

import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.CardSearchResponseData
import com.gmail.pentominto.us.mtgcardrulings.data.repository.IDefaultRepository
import com.gmail.pentominto.us.mtgcardrulings.utility.Resource
import javax.inject.Inject

interface IGetCardInfo {

    suspend operator fun invoke(input : String): Resource<CardSearchResponseData>
}

class GetCardInfoUseCase @Inject constructor(
    val repository : IDefaultRepository
) : IGetCardInfo {

    override suspend fun invoke(input : String) : Resource<CardSearchResponseData> {

        val result = repository.getCardInfo(input)

        return when (result) {
            is Resource.Success -> Resource.Success(result.data)
            is Resource.Error   -> Resource.Error("Unknown Error")
            else                -> throw Exception()
        }
    }
}