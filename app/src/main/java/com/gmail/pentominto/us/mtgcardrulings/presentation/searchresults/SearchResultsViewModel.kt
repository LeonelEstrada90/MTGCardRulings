package com.gmail.pentominto.us.mtgcardrulings.presentation.searchresults

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.pentominto.us.mtgcardrulings.utility.Resource
import com.gmail.pentominto.us.mtgcardrulings.presentation.searchresults.usecase.IGetSearchResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultsViewModel @Inject constructor(
    val useCase : IGetSearchResultsUseCase
) : ViewModel() {

    var searchState by mutableStateOf(SearchResultsState())

    private var searchJob : Job? = null

    private fun getDataFromApi(input : String) {

        viewModelScope.launch {

            when (val result = useCase(input)) {

                is Resource.Success -> {
                    result.data?.let {
                        searchState = searchState.copy(hasData = true, searchResults = result.data)
                    }
                }
                is Resource.Error   -> {
                    searchState = searchState.copy(hasData = false, isLoading = false, hasError = true)
                }
            }
        }
    }

    fun onSearchQueryChanged(newInput : String) {

        searchState = searchState.copy(isLoading = true)
        searchState = searchState.copy(searchQuery = newInput)

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            getDataFromApi(newInput)

        }
    }
}
