package com.gmail.pentominto.us.mtgcardrulings.presentation.searchresults

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.pentominto.us.mtgcardrulings.presentation.searchresults.usecase.IGetSearchResultsUseCase
import com.gmail.pentominto.us.mtgcardrulings.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultsViewModel @Inject constructor(
    val useCase : IGetSearchResultsUseCase
) : ViewModel() {

    private val _searchState : MutableState<SearchResultsState> = mutableStateOf(SearchResultsState())
    val searchState : State<SearchResultsState> = _searchState

    private var searchJob : Job? = null

    private fun getDataFromApi(input : String) {

        viewModelScope.launch {

            when (val searchResult = useCase(input)) {

                is Resource.Success -> {
                    searchResult.data?.let {
                        _searchState.value = _searchState.value.copy(hasData = true, searchResults = searchResult.data)
                    }
                }
                is Resource.Error   -> {
                    _searchState.value = _searchState.value.copy(hasData = false, isLoading = false, hasError = true)
                }
            }
        }
    }

    fun onSearchQueryChanged(newInput : String) {

        _searchState.value = _searchState.value.copy(isLoading = true)
        _searchState.value = _searchState.value.copy(searchQuery = newInput)

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            getDataFromApi(newInput)

        }
    }
}
