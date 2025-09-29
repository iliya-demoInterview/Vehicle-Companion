package net.dentabros.poi

import androidx.compose.runtime.Composable
import net.dentabros.shared.PoiList


@Composable
internal fun PoiScreen(state: PoiUIState, onAddToFavourites: (POI) -> Unit) {
    when (state) {
        PoiUIState.Error -> {}
        PoiUIState.Loading -> {}
        is PoiUIState.Success -> PoiList(state.data, onAddToFavourites)
    }
}

