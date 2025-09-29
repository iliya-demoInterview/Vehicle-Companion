package net.dentabros.vehiclecompanion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.ui.graphics.vector.ImageVector
import net.dentabros.favourites.navigation.favouritesRoute
import net.dentabros.poi.navigation.poiRoute
import net.dentabros.vehicle.navigation.vehicleRoute

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    GARAGE(vehicleRoute, "Garage", Icons.Default.MoreVert, "Garage"),
    POI(poiRoute, "POI", Icons.Default.MoreVert, "Poi"),
    FAVOURITES(favouritesRoute, "Favourites", Icons.Default.Favorite, "Favourites"),
}