package net.dentabros.create_vehicle.navigation

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import net.dentabros.create_vehicle.CreateVehicleScreen
import net.dentabros.create_vehicle.CreateVehicleViewModel

const val createVehicleRoute = "create_vehicle_route"
const val createVehicleScreen = "vehicle_screen"

fun NavController.navigateToCreateVehicle(navOptions: NavOptions? = null) {
    this.navigate(createVehicleRoute, navOptions)
}

fun NavGraphBuilder.createVehicleScreen(
    onSuccess : () -> Unit
) {
    navigation(startDestination = createVehicleScreen, route = createVehicleRoute) {
        composable(route = createVehicleScreen) {
            val createVehicleViewModel: CreateVehicleViewModel = hiltViewModel()
            CreateVehicleScreen(
                vehicle = createVehicleViewModel.vehicle,
                onSave = createVehicleViewModel::createVehicle,
                onSuccess = onSuccess,
            )
        }

    }
}