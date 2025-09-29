package net.dentabros.favourites

import net.dentabros.poi.POI

sealed class FavouritesUIState {
    data object Empty : FavouritesUIState()
    data class Success(val data : List<POI>) : FavouritesUIState()
}