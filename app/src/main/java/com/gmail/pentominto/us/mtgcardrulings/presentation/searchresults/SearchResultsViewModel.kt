package com.gmail.pentominto.us.mtgcardrulings.presentation.searchresults

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.pentominto.us.mtgcardrulings.utility.Resource
import com.gmail.pentominto.us.mtgcardrulings.presentation.searchresults.usecasae.IGetSearchResultsUseCase
import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultsViewModel @Inject constructor(
    val useCase : IGetSearchResultsUseCase
) : ViewModel() {

    private val _listOfCardsState : MutableState<Resource<List<Data>>> = mutableStateOf(Resource.Uninitialized())
    val listOfCardsState : State<Resource<List<Data>>> = _listOfCardsState

    var searchState by mutableStateOf(String())

    private var searchJob : Job? = null

    fun getSearchResults(input : String) {

        _listOfCardsState.value = Resource.Loading()
        viewModelScope.launch {

            when (val result = useCase(input)) {

                is Resource.Success -> _listOfCardsState.value = Resource.Success(result.data)
                is Resource.Error   -> _listOfCardsState.value = Resource.Error("Error")
                else                -> throw Exception()
            }
        }
    }

    fun getNewResults(newInput : String){

        searchState = newInput
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            getSearchResults(newInput)
        }

    }
}
