package net.dentabros.poi.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import net.dentabros.poi.PoiScreen
import net.dentabros.poi.PoiViewModel

const val poiRoute = "poi_route"
const val poiScreen = "poi_screen"

fun NavController.navigateToPois(navOptions: NavOptions? = null) {
    this.navigate(poiRoute, navOptions)
}

fun NavGraphBuilder.poiScreen(
) {
    navigation(startDestination = poiScreen, route = poiRoute) {
        composable(route = poiScreen) {
            val poiViewModel: PoiViewModel = hiltViewModel()
            val state by poiViewModel.uiState.collectAsStateWithLifecycle()

            PoiScreen(
                state = state,
                poiViewModel::addToFavourites
            )
        }

    }
}