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

    private val _viewState : MutableState<CardDetailsState> = mutableStateOf(CardDetailsState())
    val viewState : State<CardDetailsState> = _viewState

    private val _cardFlipToggle : MutableState<Boolean> = mutableStateOf(true)
    val cardFlipToggle : State<Boolean> = _cardFlipToggle

    fun flipCard() {
//        _cardFlipToggle.value = ! _cardFlipToggle.value
        _viewState.value.copy(frontSide = !viewState.value.frontSide)
    }

    var toggleState = mutableStateOf(true)


    fun getSingleCardData(cardId : String) {

        viewModelScope.launch {

            _viewState.value = _viewState.value.copy(isLoading = true)

            when (val cardData = cardInfoUc(cardId)) {

                is Resource.Success -> {
                    cardData.data?.let {
                        _viewState.value = _viewState.value.copy(
                            infoForDisplay = cardData.data
                        )
                    }
                }
                is Resource.Error   -> {
//                    detailsState = detailsState.copy(hasData = false, isLoading = false, hasError = true)
                }
            }

            when (val rulingsData = rulingsDataUc(cardId)) {

                is Resource.Success -> {
                    rulingsData.data?.let {
                        _viewState.value = _viewState.value.copy(
                            rulingsData = rulingsData.data,
                            isLoading = false
                        )
                    }
                }
                is Resource.Error   -> {
//                    detailsState = detailsState.copy(hasData = false, isLoading = false, hasError = true)
                }
            }
        }
    }
}