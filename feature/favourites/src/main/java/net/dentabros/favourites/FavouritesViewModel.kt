package net.dentabros.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import net.dentabros.poi.POIRepository
import javax.inject.Inject

@HiltViewModel
internal class FavouritesViewModel @Inject constructor(
    private val poiRepository: POIRepository
) : ViewModel() {

    val favouritesUIState: StateFlow<FavouritesUIState> =
        poiRepository.getFavourites().map {
            if (it.isEmpty())
                FavouritesUIState.Empty
            else
                FavouritesUIState.Success(it)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FavouritesUIState.Empty,
        )


}