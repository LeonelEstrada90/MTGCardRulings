package com.gmail.pentominto.us.mtgcardrulings.favorites

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.pentominto.us.mtgcardrulings.Resource
import com.gmail.pentominto.us.mtgcardrulings.favorites.usecasae.IGetSearchResultsUseCase
import com.gmail.pentominto.us.mtgcardrulings.model.Card
import com.gmail.pentominto.us.mtgcardrulings.model.cardssearchresponse.CardSearchResponse
import com.gmail.pentominto.us.mtgcardrulings.model.cardssearchresponse.Data
import com.gmail.pentominto.us.mtgcardrulings.repository.IDefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    val useCase : IGetSearchResultsUseCase
) : ViewModel() {

    private val _listOfCards : MutableState<Resource<List<Data>>> = mutableStateOf(Resource.Uninitialized())
    val listOfCards : State<Resource<List<Data>>> = _listOfCards

fun getSearchResults(input : String) {

        _listOfCards.value = Resource.Loading()
        viewModelScope.launch {

            val result = useCase(input)
            _listOfCards.value = result
        }
    }
}
