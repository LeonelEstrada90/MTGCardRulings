package com.gmail.pentominto.us.mtgcardrulings.presentation.searchresults

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.pentominto.us.mtgcardrulings.utility.Resource
import com.gmail.pentominto.us.mtgcardrulings.presentation.searchresults.usecasae.IGetSearchResultsUseCase
import com.gmail.pentominto.us.mtgcardrulings.data.model.cardssearchresponse.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultsViewModel @Inject constructor(
    val useCase : IGetSearchResultsUseCase
) : ViewModel() {

    private val _listOfCards : MutableState<Resource<List<Data>>> = mutableStateOf(Resource.Uninitialized())
    val listOfCards : State<Resource<List<Data>>> = _listOfCards

fun getSearchResults(input : String) {

        _listOfCards.value = Resource.Loading()
        viewModelScope.launch {

            when (val result = useCase(input)){

                is Resource.Success -> _listOfCards.value = Resource.Success(result.data)
                is Resource.Error   -> _listOfCards.value = Resource.Error("Error in VM")
                else                -> throw Exception()
            }
        }
    }
}
