package com.gmail.pentominto.us.mtgcardrulings.presentation.details.usecase

import android.util.Log
import com.gmail.pentominto.us.mtgcardrulings.data.model.CardForDisplayScreen
import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.CardSearchResponseData
import com.gmail.pentominto.us.mtgcardrulings.data.model.toCardForDisplay
import com.gmail.pentominto.us.mtgcardrulings.data.repository.IDefaultRepository
import com.gmail.pentominto.us.mtgcardrulings.utility.Resource
import javax.inject.Inject

interface IGetCardInfo {

    suspend operator fun invoke(input : String): Resource<CardForDisplayScreen>
}

class GetCardInfoUseCase @Inject constructor(
    private val repository : IDefaultRepository
) : IGetCardInfo {

    override suspend fun invoke(input : String) : Resource<CardForDisplayScreen> {

        val result = repository.getCardInfo(input)

        return when (result) {
            is Resource.Success -> Resource.Success(convertToCardForDisplay(result.data))
            is Resource.Error   -> Resource.Error("Unknown Error")
        }
    }

    private fun convertToCardForDisplay(data : CardSearchResponseData?) : CardForDisplayScreen {

        return when (data?.layout){

            "normal" -> data.toCardForDisplay(null, null, data.layout)
            "transform" ->  data.toCardForDisplay(data.card_faces?.get(0)?.image_uris?.large, data.card_faces?.get(1)?.image_uris?.large, data.layout)
            else        -> throw Exception()
        }
    }
}