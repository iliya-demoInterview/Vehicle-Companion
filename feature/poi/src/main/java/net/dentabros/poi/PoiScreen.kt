package net.dentabros.poi

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import net.dentabros.shared.PoiList


@Composable
internal fun PoiScreen(
    state: PoiUIState,
    onAddToFavourites: (POI) -> Unit,
    onDeleteFromFavourites: (POI) -> Unit,
    onReload: () -> Unit
) {
    when (state) {
        PoiUIState.Error -> {
            Column {
                Text(text = "Something went wrong")
                Button(onClick = { onReload() }) {
                    Text(text = "Try again")
                }
            }
        }

        PoiUIState.Loading -> {
            Text(text = "Loading...")
        }

        is PoiUIState.Success -> PoiList(state.data, onAddToFavourites, onDeleteFromFavourites)
    }
}

