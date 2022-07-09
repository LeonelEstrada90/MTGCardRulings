package com.gmail.pentominto.us.mtgcardrulings.presentation.details

import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.pentominto.us.mtgcardrulings.presentation.details.usecase.IGetCardInfo
import com.gmail.pentominto.us.mtgcardrulings.presentation.details.usecase.IGetRulingsData
import com.gmail.pentominto.us.mtgcardrulings.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    val cardInfoUc : IGetCardInfo,
    val rulingsDataUc : IGetRulingsData
) : ViewModel() {

    var detailsState by mutableStateOf(CardDetailsState())


    fun getSingleCardData(cardName : String) {

        viewModelScope.launch {

            when (val cardData = cardInfoUc(cardName)) {

                is Resource.Success -> {
                    cardData.data?.let {
                        detailsState = detailsState.copy(cardSearchResponseData = cardData.data)
                    }
                }
                is Resource.Error   -> {
//                    detailsState = detailsState.copy(hasData = false, isLoading = false, hasError = true)
                }
            }
        }
    }

    fun getRulingsData(id : String) {

        viewModelScope.launch {

            when (val rulingsData = rulingsDataUc(id)) {

                is Resource.Success -> {
                    rulingsData.data?.let {
                        detailsState = detailsState.copy(rulingsData = rulingsData.data)
                    }
                }
                is Resource.Error   -> {
//                    detailsState = detailsState.copy(hasData = false, isLoading = false, hasError = true)
                }
            }
        }
    }
}