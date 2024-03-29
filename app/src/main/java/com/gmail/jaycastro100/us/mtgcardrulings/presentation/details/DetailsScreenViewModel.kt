package com.gmail.jaycastro100.us.mtgcardrulings.presentation.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.jaycastro100.us.mtgcardrulings.presentation.details.usecase.IGetCardInfo
import com.gmail.jaycastro100.us.mtgcardrulings.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    val cardInfoUc : IGetCardInfo
) : ViewModel() {

    private val _viewState : MutableState<CardDetailsState> = mutableStateOf(CardDetailsState())
    val viewState : State<CardDetailsState> = _viewState

    fun flipCard() {
        _viewState.value = viewState.value.copy(isShowingFrontSide = ! _viewState.value.isShowingFrontSide)
    }

    fun getSingleCardData(cardId : String) {

        viewModelScope.launch {

            _viewState.value = _viewState.value.copy(loading = true)

            when (val cardData = cardInfoUc(cardId)) {

                is Resource.Success -> {
                    cardData.data?.let {
                        _viewState.value = _viewState.value.copy(
                            screenInfo = cardData.data,
                            loading = false,
                            error = false
                        )
                    }
                }
                is Resource.Error   -> {
                    _viewState.value = _viewState.value.copy(
                        loading = false,
                        error = true
                    )
                }
            }
        }
    }
}