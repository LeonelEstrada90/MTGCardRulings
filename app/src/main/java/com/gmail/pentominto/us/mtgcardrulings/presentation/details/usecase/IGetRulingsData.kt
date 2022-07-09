package com.gmail.pentominto.us.mtgcardrulings.presentation.details.usecase

import com.gmail.pentominto.us.mtgcardrulings.data.model.rulingsresponse.RulingsResponse
import com.gmail.pentominto.us.mtgcardrulings.data.model.rulingsresponse.RulingsResponseData
import com.gmail.pentominto.us.mtgcardrulings.data.repository.IDefaultRepository
import com.gmail.pentominto.us.mtgcardrulings.utility.Resource
import javax.inject.Inject

interface IGetRulingsData {

    suspend operator fun invoke(input : String):     Resource<List<RulingsResponseData>>

}

class GetRulingsData @Inject constructor(
    val repository : IDefaultRepository
) : IGetRulingsData {

    override suspend fun invoke(input : String) : Resource<List<RulingsResponseData>> {

        val result = repository.getRulingsData(input)

        return when (result) {
            is Resource.Success -> Resource.Success(result.data?.data)
            is Resource.Error   -> Resource.Error("Unknown Error")
            else                -> throw Exception()
        }
    }
}