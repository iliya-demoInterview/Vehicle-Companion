package net.dentabros.favourites

import androidx.compose.runtime.Composable
import net.dentabros.shared.PoiList

@Composable
fun FavouritesScreen(state: FavouritesUIState) {
    when (state) {
        is FavouritesUIState.Empty -> {}
        is FavouritesUIState.Success -> PoiList(state.data, {}, onDeleteFromFavourites = {})
    }
}