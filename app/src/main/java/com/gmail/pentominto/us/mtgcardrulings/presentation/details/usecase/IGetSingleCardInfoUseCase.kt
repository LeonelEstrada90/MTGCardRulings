package com.gmail.pentominto.us.mtgcardrulings.presentation.details.usecase

import android.util.Log
import com.gmail.pentominto.us.mtgcardrulings.data.model.CardForDisplayScreen
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

        if (result.data?.layout == "transform"){

            val nice = result.data.toCardForDisplay(result.data.card_faces?.get(0)?.image_uris?.large, result.data.card_faces?.get(1)?.image_uris?.large)
            Log.d(
                "TAG",
                "invoke: ${nice.transformFrontImage}"
            )

            Log.d(
                "TAG",
                "invoke: ${nice.transformBackImage}"
            )

        }

        val infoToDisplay = result.data?.toCardForDisplay(null, null)

        return when (result) {
            is Resource.Success -> Resource.Success(infoToDisplay)
            is Resource.Error   -> Resource.Error("Unknown Error")
            else                -> throw Exception()
        }
    }
}