package net.dentabros.vehicle.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import net.dentabros.vehicle.GarageScreen
import net.dentabros.vehicle.GarageViewModel

const val vehicleRoute = "vehicle_route"
const val vehicleScreen = "vehicle_screen"

fun NavController.navigateToVehicles(navOptions: NavOptions? = null) {
    this.navigate(vehicleRoute, navOptions)
}

fun NavGraphBuilder.garageScreen(
    createVehicle: () -> Unit
) {
    navigation(startDestination = vehicleScreen, route = vehicleRoute) {
        composable(route = vehicleScreen) {
            val garageViewModel: GarageViewModel = hiltViewModel()
            val state by garageViewModel.garageUIState.collectAsStateWithLifecycle()

            GarageScreen(
                state = state,
                createVehicle = createVehicle
            )
        }

    }
}