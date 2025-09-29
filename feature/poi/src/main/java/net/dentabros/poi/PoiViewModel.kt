package net.dentabros.poi

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PoiViewModel @Inject constructor(
    private val poiRepository: POIRepository
) : ViewModel() {

    private val _state = MutableStateFlow<PoiUIState>(PoiUIState.Loading)
    val uiState = _state.asStateFlow()

    init {
        getPoi()
    }

    fun getPoi(){
        viewModelScope.launch {
            poiRepository.getPOIs().onSuccess {
                _state.value = PoiUIState.Success(it)
            }.onFailure {
                _state.value = PoiUIState.Error
            }
        }
    }


    fun addToFavourites(poi: POI){
        viewModelScope.launch {
            poiRepository.insert(poi)
        }
    }
}