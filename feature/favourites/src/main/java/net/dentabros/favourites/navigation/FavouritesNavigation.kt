package net.dentabros.favourites.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import net.dentabros.favourites.FavouritesScreen
import net.dentabros.favourites.FavouritesViewModel

const val favouritesRoute = "favourites_route"
const val favouritesScreen = "favourites_screen"

fun NavController.navigateToFavourites(navOptions: NavOptions? = null) {
    this.navigate(favouritesRoute, navOptions)
}

fun NavGraphBuilder.favouritesScreen(
) {
    navigation(startDestination = favouritesScreen, route = favouritesRoute) {
        composable(route = favouritesScreen) {
            val favouritesViewModel: FavouritesViewModel = hiltViewModel()
            val state by favouritesViewModel.favouritesUIState.collectAsStateWithLifecycle()

            FavouritesScreen(
                state = state,
            )
        }
    }
}