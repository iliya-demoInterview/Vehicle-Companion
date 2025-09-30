package net.dentabros.favourites

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import net.dentabros.shared.PoiList

@Composable
fun FavouritesScreen(state: FavouritesUIState) {
    when (state) {
        is FavouritesUIState.Empty -> {Text("No favourites")}
        is FavouritesUIState.Success -> PoiList(state.data, {}, onDeleteFromFavourites = {})
    }
}