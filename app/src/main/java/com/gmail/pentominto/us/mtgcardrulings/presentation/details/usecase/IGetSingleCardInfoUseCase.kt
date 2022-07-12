package com.gmail.pentominto.us.mtgcardrulings.presentation.details.usecase

import com.gmail.pentominto.us.mtgcardrulings.data.model.CardForDisplay
import com.gmail.pentominto.us.mtgcardrulings.data.model.cardlayouts.cardtransformlayout.CardTransformLayout
import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.CardSearchResponseData
import com.gmail.pentominto.us.mtgcardrulings.data.model.toCardForDisplay
import com.gmail.pentominto.us.mtgcardrulings.data.repository.IDefaultRepository
import com.gmail.pentominto.us.mtgcardrulings.utility.Resource
import javax.inject.Inject

interface IGetCardInfo {

    suspend operator fun invoke(input : String): Resource<CardForDisplay>
}

class GetCardInfoUseCase @Inject constructor(
    private val repository : IDefaultRepository
) : IGetCardInfo {

    override suspend fun invoke(input : String) : Resource<CardForDisplay> {

        val result = repository.getCardInfo(input)

        val infoToDisplay = result.data?.toCardForDisplay()

        return when (result) {
            is Resource.Success -> Resource.Success(infoToDisplay)
            is Resource.Error   -> Resource.Error("Unknown Error")
            else                -> throw Exception()
        }
    }
}