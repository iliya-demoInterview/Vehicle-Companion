package net.dentabros.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch
import net.dentabros.poi.POI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PoiList(
    items: List<POI>,
    onAddToFavourites: (POI) -> Unit,
    onDeleteFromFavourites: (POI) -> Unit
) {
    val listState = rememberLazyListState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedItem: POI? by remember { mutableStateOf(null) }
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {

        items(items, key = { it.id }) { poi ->
            PoiItem(poi, onAddToFavourites, onDeleteFromFavourites) {
                coroutineScope.launch {
                    selectedItem = it
                    showBottomSheet = true
                    sheetState.expand()
                }

            }
        }

    }

    selectedItem?.let {
        if (showBottomSheet && sheetState.isVisible) {
            Details(it, sheetState) {
                coroutineScope.launch {
                    showBottomSheet = false
                    sheetState.hide()
                }

            }
        }
    }

}

@Composable
fun PoiItem(
    item: POI,
    onAddToFavourites: (POI) -> Unit,
    onDeleteFromFavourites: (POI) -> Unit,
    onShowDetails: (POI) -> Unit
) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Row {

            AsyncImage(
                model = item.imageUrl, contentDescription = "Vehicle Image",
                modifier = Modifier.size(120.dp),
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                item.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }


                Text(text = item.primaryCategory)

                Row {

                    Icon(imageVector = Icons.Default.Star, contentDescription = "Rating")
                    Text(text = item.rating.toString())
                }



                Button(onClick = {
                    onShowDetails(item)
                }) {
                    Text(text = "Details")
                }

            }


            IconButton(onClick = {
                if (!item.isFavourite) {
                    onAddToFavourites(item)
                } else {
                    onDeleteFromFavourites(item)
                }
            }) {
                val icon =
                    if (item.isFavourite) Icons.Sharp.Favorite else Icons.Sharp.FavoriteBorder
                Icon(
                    imageVector = icon, contentDescription = "Rating"
                )
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Details(item: POI, sheetState: SheetState, onDismiss: () -> Unit) {
    val uriHandler = LocalUriHandler.current

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss
        },
        sheetState = sheetState,
    ) {
        AsyncImage(
            model = item.imageUrl, contentDescription = "Vehicle Image",
            modifier = Modifier.size(120.dp),
        )

        item.name?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleLarge,
            )
        }

        Text(text = item.primaryCategory)

        Row {
            Icon(imageVector = Icons.Default.Star, contentDescription = "Rating")
            Text(text = item.rating.toString())
        }


        TextButton(onClick = { uriHandler.openUri(item.url) }) {
            Text("Open")
        }


    }
}