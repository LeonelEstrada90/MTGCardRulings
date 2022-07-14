package com.gmail.jaycastro100.us.mtgcardrulings.presentation.details.usecase

import com.gmail.jaycastro100.us.mtgcardrulings.data.model.CardForDisplayScreen
import com.gmail.jaycastro100.us.mtgcardrulings.data.model.cardssearchresponse.CardSearchResponseData
import com.gmail.jaycastro100.us.mtgcardrulings.data.model.cardssearchresponse.Legalities
import com.gmail.jaycastro100.us.mtgcardrulings.data.model.toCardForDisplay
import com.gmail.jaycastro100.us.mtgcardrulings.data.repository.IDefaultRepository
import com.gmail.jaycastro100.us.mtgcardrulings.utility.Resource
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

interface IGetCardInfo {

    suspend operator fun invoke(input : String) : Resource<CardForDisplayScreen>
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

    private fun convertToCardForDisplay(data : CardSearchResponseData?) : CardForDisplayScreen? {

        val legalities = mutableListOf<String>()

        for (format in Legalities::class.memberProperties) {
            if (data?.legalities?.let {
                    format.get(it).toString().contains(
                        "not_legal",
                        true
                    )
                } == true
            ) {
                /**
                 * Filtering out "not_legal", could also look for "legal" instead
                 */
            } else {
                when (format.name) {
                    "paupercommander" -> {
                        legalities.add("PC")
                    }
                    "historicbrawl"   -> {
                        legalities.add("HB")
                    }
                    else              -> {
                        legalities.add(format.name.replaceFirstChar { it.uppercase() })
                    }
                }
            }
        }
        return when (data?.layout) {

            "transform" -> data.toCardForDisplay(
                data.card_faces?.get(0)?.image_uris?.large,
                data.card_faces?.get(1)?.image_uris?.large,
                data.layout,
                legalities
            )
            "modal_dfc" -> data.toCardForDisplay(
                data.card_faces?.get(0)?.image_uris?.large,
                data.card_faces?.get(1)?.image_uris?.large,
                data.layout,
                legalities
            )
            "reversible" -> data.toCardForDisplay(
                data.card_faces?.get(0)?.image_uris?.large,
                data.card_faces?.get(1)?.image_uris?.large,
                data.layout,
                legalities
            )
            else -> data?.toCardForDisplay(
                null,
                null,
                data.layout,
                legalities
            )
        }
    }
}